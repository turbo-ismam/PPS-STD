package Enemy

import Controller.GameController
import Controller.Wave.Wave
import Model.Enemy._
import Model.Grid.{Grid, GridController}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EnemyWaveTest extends AnyFunSuite {

  val gridController = GridController(1)
  val controller: GameController = new GameController("player-test", mapDifficulty = 1)
  val e: Enemy = Enemy(Easy,gridController)
  var w: Wave = new Wave(1,controller)

  @Test def simpleTest(): Unit = {
    assertFalse(e.isAlive)
    e.spawn()
    assertTrue(e.isAlive)
    assertEquals((1,0),e.enemyCurrentPosition())
  }

  test("enemy test") {
    assertFalse(e.isAlive)
    e.spawn()
    assertTrue(e.isAlive)
    e.death()
    assertTrue(e.isAlive)
    e.move(1)
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(0,e.enemyCurrentPosition().xPlace)
    e.move(1)
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(0,e.enemyCurrentPosition().xPlace)
    for(n <-1 to 100) e.move(1)
    assertEquals(1,e.enemyCurrentPosition().yPlace)
    assertEquals(0,e.enemyCurrentPosition().xPlace)
    e.destroy()
    e.death()
    assertFalse(e.isAlive)
  }

  test("wave test") {
    assertTrue(w.hasEnemies)
    w.clearWave()
    assertFalse(w.hasEnemies)
    w = w.nextWave()
    assertTrue(w.hasEnemies)
    w.update()
    assertTrue(w.hasEnemies)
    for(n <-1 to 300) w.update()
    assertTrue(w.hasEnemies)
    for(n <-1 to 1000) w.update()
    assertFalse(w.hasEnemies)
  }

}
