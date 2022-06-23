package Controller

import Controller.Tower.Tower
import Model.Enemy.{Easy, Enemy, EnemyImpl}
import Model.Tower.{CircularRadiusTower, ShooterTower, TowerTypes}
import org.junit.Assert.assertTrue
import org.scalatest.funsuite.AnyFunSuite

class GameControllerTest extends AnyFunSuite {

  //Initialize controller
  val controller: GameController = new GameController("player-test", mapDifficulty = 1)

  test("Game controller check initialization") {
    //Check if player is initialize
    assert(controller.player.playerName.equals("player-test"))
    //Check if grid controller is
    assert(controller.gridController != null)
  }

  test("Game controller test towers") {
    controller.initializeTower()
    //Check if default tower is setup
    assertTrue(controller.availableTowers.size.equals(3))

    //Try getting base tower from a map
    val base_tower: Tower = controller.availableTowers.get(TowerTypes.BASE_TOWER).get
    assertTrue(base_tower != null)

    //Try getting flame tower from a map
    val flame_tower: Tower = controller.availableTowers.get(TowerTypes.FLAME_TOWER).get
    assertTrue(flame_tower != null)

    //Try building tower
    controller.buildTower(flame_tower)
    assertTrue(!controller.selectedTower.isEmpty)

    //Add tower to list
    controller += flame_tower
    assertTrue(controller.towers.size == 1)
    controller += base_tower
    assertTrue(controller.towers.size == 2)
    controller -= base_tower
    assertTrue(controller.towers.size == 1)

    controller.resetSelectedTower()
    assertTrue(controller.selectedTower.isEmpty)
  }

  test("Game controller test enemy") {
    val e: Enemy = new EnemyImpl(Easy, controller.gridController)
    val e2: Enemy = new EnemyImpl(Easy, controller.gridController)
    controller += e
    assertTrue(controller.enemies.size == 1)
    controller += e2
    assertTrue(controller.enemies.size == 2)
    controller -= e
    assertTrue(controller.enemies.size == 1 && controller.enemies.head.equals(e2))
    controller.removeEnemy(e2)
    assertTrue(controller.junkEnemies.size == 1)
  }
}
