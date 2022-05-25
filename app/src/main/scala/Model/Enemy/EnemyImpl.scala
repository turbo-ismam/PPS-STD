package Model.Enemy

import Model.Grid.{Grid, Tile}

class EnemyImpl(enemytype: EnemyType) extends Enemy {

    //private val startTile = grid
    var health: Int = enemytype.health
    val speed: Int = enemytype.speed
    var alive: Boolean = false

  override def draw(): Unit = null

  override def spawn(): Unit = {
    this.alive = true
  }

  override def move(t: Tile): Unit = ???

  override def currentTile(): Tile = ???

  override def takeDamage( i: Int): Unit = {
    this.health -= i
    if (this.health <= 0) {
      this.death()
    }
  }

  override def isAlive(): Boolean = {
    alive
  }

  override def death(): Unit = {
    if(this.health <= 0){
      this.alive = false
    }
  }

}
