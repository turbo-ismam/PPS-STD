package Enemy

import Controller.{GameController, GridController}
import Model.Enemy._
import Model.Grid.Grid
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EnemyTest extends AnyFunSuite {

  val gridController = new GridController(1)
  val gameController: GameController = new GameController("jojo", 0)
  val e: Enemy = new EnemyImpl(Easy,gridController)
  val w: Wave = new WaveImpl(1,gameController)

  @Test def simpleTest(): Unit = {
    assertFalse(e.isAlive())
    e.spawn()
    assertTrue(e.isAlive())
    assertEquals((1,0),e.enemyCurrentPosition())
  }

  test("enemy test") {
    assertFalse(e.isAlive())
    e.spawn()
    assertTrue(e.isAlive())
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(0,e.enemyCurrentPosition().xPlace)
    e.move()
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(1,e.enemyCurrentPosition().xPlace)
    e.move()
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(2,e.enemyCurrentPosition().xPlace)
    e.move()
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(3,e.enemyCurrentPosition().xPlace)
    e.move()
    assertEquals(2,e.enemyCurrentPosition().yPlace)
    assertEquals(3,e.enemyCurrentPosition().xPlace)
    e.move()
    assertEquals(3,e.enemyCurrentPosition().yPlace)
    assertEquals(3,e.enemyCurrentPosition().xPlace)
    e.move()
    assertEquals(3,e.enemyCurrentPosition().yPlace)
    assertEquals(4,e.enemyCurrentPosition().xPlace)
    e.death()
    assertTrue(e.isAlive())
  }

  test("wave test"){
    w.populate(3,Easy,gridController)
    assertTrue(w.hasEnemies())
  }

}
