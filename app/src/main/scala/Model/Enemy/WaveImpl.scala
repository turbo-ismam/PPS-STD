package Model.Enemy

import Controller.{GameController, GridController}
import Model.Grid.{Grid, Tile}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class WaveImpl(nWave: Int, gameController: GameController) extends Wave {

  val listHelper = new ListBuffer[Enemy]
  val enemyList = populate(this.getWave(),gameController.getGridController)
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

  override def populate(waveNumber: Int, grid: GridController): ListBuffer[Enemy] = waveNumber match {
    case it if 0 until 5 contains it => for(x <- 1 to waveNumber*2 ){
      listHelper += new EnemyImpl(Easy, grid)
    }
      listHelper
    case it if 5 until 10 contains it => for(x <- 1 to waveNumber ){
      listHelper += new EnemyImpl(Medium, grid)
    }
      listHelper
    case _ => for(x <- 1 to waveNumber ){
      listHelper += new EnemyImpl(Hard, grid)
    }
      listHelper
  }

  override def spawn(): Unit = {
    if(this.hasEnemies()){
      enemyList(0).spawn()
      gameController += enemyList.remove(0)
    }
  }

  override def nextWave(): WaveImpl = {
    new WaveImpl(this.getWave() + 1, gameController)
  }

  override def hasEnemies(): Boolean = {
    !enemyList.isEmpty
  }

  override def clearWave(): Unit = {
    enemyList.clear()
  }

}
