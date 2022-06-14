package Tower

import Controller.GameController
import Controller.Tower.Tower
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import org.scalatest.funsuite.AnyFunSuite

class TowerGenerationTest extends AnyFunSuite {

  import Model.Tower._

  //Initialize controller
  val controller: GameController = new GameController("player-test", mapDifficulty = 1)

  test("Create Base tower") {
    val towerType: TowerType = TowerType(BASE_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, 0.0, 0.0, controller)
    assert(tower.name().equals(towerType.name))
    assert(tower.player.playerName.equals(controller.player.playerName))
  }

  test("Create Flame tower") {
    val towerType: TowerType = TowerType(FLAME_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, 0.0, 0.0, controller)
    assert(tower.name().equals(towerType.name))
    assert(tower.player.playerName.equals(controller.player.playerName))
  }

  test("Create Cannon tower") {
    val towerType: TowerType = TowerType(CANNON_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, 0.0, 0.0, controller)
    assert(tower.name().equals(towerType.name))
    assert(tower.player.playerName.equals(controller.player.playerName))
  }

  test("Tower positioning"){
    val x: Int = (31.6/64).toInt
    val y: Int = (51.31/64).toInt
    println(x + " - " + y)

    val x2: Int = (154.64/64).toInt
    val y2: Int = (51.14/64).toInt
    println(x2 + " - " + y2)
  }
}
