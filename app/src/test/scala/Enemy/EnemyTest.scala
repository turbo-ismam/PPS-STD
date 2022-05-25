package Enemy

import Model.Enemy.{Easy, Enemy, EnemyImpl}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

class EnemyTest {

  @RunWith(classOf[JUnitRunner])
  class AppSuite extends AnyFunSuite {

    val e: Enemy = new EnemyImpl(Easy)

    @Test def simpleTest(): Unit ={
      assertFalse(e.isAlive())
      e.spawn()
      assertTrue(e.isAlive())
    }
    
  }
}
