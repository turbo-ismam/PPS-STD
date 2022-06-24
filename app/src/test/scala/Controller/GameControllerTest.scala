package Controller

import Controller.Tower.Tower
import Model.Enemy.{Easy, Enemy}
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
    assert(controller.getGridController != null)
  }

  test("Game controller test towers") {
    controller.setupAvailableTowers()
    //Check if default tower is setup
    assertTrue(controller.available_towers.size.equals(3))

    //Try getting base tower from a map
    val base_tower: Tower = controller.available_towers.get(TowerTypes.BASE_TOWER).get
    assertTrue(base_tower != null)

    //Try getting flame tower from a map
    val flame_tower: Tower = controller.available_towers.get(TowerTypes.FLAME_TOWER).get
    assertTrue(flame_tower != null)

    //Try building tower
    controller.buildTower(flame_tower)
    assertTrue(!controller.selected_tower.isEmpty)

    //Add tower to list
    controller += flame_tower
    assertTrue(controller.towers.size == 1)
    controller += base_tower
    assertTrue(controller.towers.size == 2)
    controller -= base_tower
    assertTrue(controller.towers.size == 1)

    controller.resetSelectedTower()
    assertTrue(controller.selected_tower.isEmpty)
  }

  test("Game controller test enemy") {
    val e: Enemy = Enemy(Easy, controller.getGridController)
    val e2: Enemy = Enemy(Easy, controller.getGridController)
    controller += e
    assertTrue(controller.enemies.size == 1)
    controller += e2
    assertTrue(controller.enemies.size == 2)
    controller -= e
    assertTrue(controller.enemies.size == 1 && controller.enemies.head.equals(e2))
    controller.addToRemoveEnemy(e2)
    assertTrue(controller.toRemoveEnemies.size == 1)
  }
}
