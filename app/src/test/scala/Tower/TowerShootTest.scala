package Tower

import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.{Easy, Enemy}
import Model.Tower.TowerType
import Model.Tower.TowerTypes.{BASE_TOWER, FLAME_TOWER}
import Utility.WayPoint
import org.junit.Assert.{assertFalse, assertTrue}
import org.junit.runner.RunWith
import org.scalatest.PrivateMethodTester
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TowerShootTest extends AnyFunSuite with PrivateMethodTester {

  val controller: GameController = new GameController("player-test", mapDifficulty = 1)
  val e: Enemy = Enemy(Easy, controller.gridController)

  test("Create tower, choose target and shoot") {
    val xPos: Int = (104.0 / 64).toInt * 64
    val yPos: Int = (167.0 / 64).toInt * 64
    //Create tower
    val towerType: TowerType = TowerType(BASE_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, WayPoint(xPos, yPos), controller)
    //Spawn enemy
    e.spawn()
    //Check if enemy is in range
    assertTrue(tower.towerType.inRange(e))
    //Add enemy to list
    controller += e
    //Choose target, it should choose the created enemy
    tower.towerType.chooseTarget()
    //Check if tower choose the enemy
    assert(!tower.towerType.current_target.isEmpty)
    //Shoot enemy
    tower.towerType.attack()
    //Projectile list not be empty, because attack function create an projectile
    assert(!tower.projectiles.isEmpty)

    //Update and check if projectile colliding with enemy
    tower.projectiles.head.update(0.2)
    assert(tower.projectiles.head.alive == false)
  }

  test("Create tower, but no enemy in range") {
    val xPos: Int = (600.0 / 64).toInt * 64
    val yPos: Int = (600.0 / 64).toInt * 64
    //Create tower
    val towerType: TowerType = TowerType(BASE_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, WayPoint(xPos, yPos), controller)
    //Spawn enemy
    e.spawn()
    //Check if enemy is in range
    assertFalse(tower.towerType.inRange(e))

    //Choose target, it should choose the created enemy
    tower.towerType.chooseTarget()
    //Check if tower choose the enemy
    assert(tower.towerType.current_target.isEmpty)
    assertTrue(tower.projectiles.isEmpty)
  }

  test("Circular radius tower colliding test") {

    val e2: Enemy = Enemy(Easy, controller.gridController)
    val xPos: Int = (104.0 / 64).toInt * 64
    val yPos: Int = (167.0 / 64).toInt * 64
    //Create tower
    val towerType: TowerType = TowerType(FLAME_TOWER)
    val tower: Tower = new Tower(towerType, controller.player, WayPoint(xPos, yPos), controller)
    val circleRadius = tower.towerPosition.circularRadius(tower.rangeInTiles)
    //Spawn enemy
    e.spawn()
    e2.spawn()
    //Check if enemy is in range
    assertTrue(tower.towerType.isColliding(circleRadius, e))
    assertTrue(tower.towerType.isColliding(circleRadius, e2))

    val xPos2: Int = (10000.0 / 64).toInt * 64
    val yPos2: Int = (10000.0 / 64).toInt * 64
    val tower2: Tower = new Tower(towerType, controller.player, WayPoint(xPos2, yPos2), controller)
    val circleRadius2 = tower2.towerPosition.circularRadius(tower.rangeInTiles)

    assertFalse(tower.towerType.isColliding(circleRadius2, e))
    assertFalse(tower.towerType.isColliding(circleRadius2, e2))

  }
}
