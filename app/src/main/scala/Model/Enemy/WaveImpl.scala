package Model.Enemy

import Controller.{GameController, GridController}
import Model.Grid.{Grid, Tile}

import scala.collection.mutable

class WaveImpl(nWave: Int, gameController: GameController) extends Wave {

  var enemyList = mutable.Buffer[Enemy]()

  var tick: Int = 0

  override def update(delta: Double): Unit = {
    if (tick >= 200) {
      this.spawn()
      tick = 0
    }
    else{
      tick += 1
    }
  }

  override def getWave(): Int = {
    this.nWave
  }

  override def populate(i: Int, enemy: EnemyType, grid: GridController): Unit = {
    var x = 0
    for(x <- 1 to i ){
      enemyList += new EnemyImpl(enemy, grid)
    }
  }

  override def spawn(): Unit = {
    if(this.hasEnemies()){
      enemyList(0).spawn()
      gameController += enemyList.remove(0)
    }
  }

  override def nextWave(): Wave = {
    new WaveImpl(this.getWave()+1,gameController)
  }

  override def hasEnemies(): Boolean = {
    !enemyList.isEmpty
  }

  override def clearWave(): Unit = {
    enemyList.clear()
  }

}
