package Tower

import Controller.GameController
import Controller.Tower.Tower
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Utility.WayPoint
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TowerGenerationTest extends AnyFunSuite {

  import Model.Tower._

  //Initialize controller
  val controller: GameController = new GameController("player-test", mapDifficulty = 1)

  test("Create Base tower") {
    val towerType: TowerType = TowerType(BASE_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, WayPoint(0.0, 0.0), controller)
    assert(tower.towerType.towerType.equals(BASE_TOWER))
    assert(tower.player.playerName.equals(controller.player.playerName))
  }

  test("Create Flame tower") {
    val towerType: TowerType = TowerType(FLAME_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, WayPoint(0.0, 0.0), controller)
    assert(tower.towerType.towerType.equals(FLAME_TOWER))
    assert(tower.player.playerName.equals(controller.player.playerName))
  }

  test("Create Cannon tower") {
    val towerType: TowerType = TowerType(CANNON_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, WayPoint(0.0, 0.0), controller)
    assert(tower.towerType.towerType.equals(CANNON_TOWER))
    assert(tower.player.playerName.equals(controller.player.playerName))
  }

}
