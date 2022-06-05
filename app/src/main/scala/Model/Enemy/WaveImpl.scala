package Model.Enemy

import Model.Grid.Grid

import scala.collection.mutable

class WaveImpl(nWave: Int) extends Wave {

  var enemyList = mutable.Buffer[Enemy]()
  var enemySpawnedList = mutable.Buffer[Enemy]()
  var tick: Int = 0

  override def update(delta: Double): Unit = {
    if (tick >= 200) {
      this.spawn()
      tick = 0
    }
    else{
      tick += 1
    }
    enemySpawnedList.foreach(f => f.update(delta))
  }

  override def getWave(): Int = {
    this.nWave
  }

  override def populate(i: Int, e: EnemyType, g: Grid): Unit = {
    var x = 0
    for(x <- 1 to i ){
      enemyList += new EnemyImpl(e, g)
    }
  }

  override def spawn(): Unit = {
    if(this.hasEnemies()){
      enemyList(0).spawn()
      enemySpawnedList += enemyList.remove(0)
    }
    //enemyList.foreach(e => e.spawn())
  }

  override def nextWave(): Wave = {
    new WaveImpl(this.getWave()+1)
  }

  override def hasEnemies(): Boolean = {
    !enemyList.isEmpty
  }

  override def clearWave(): Unit = {
    enemyList.clear()
  }

}
