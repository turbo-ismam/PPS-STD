package Model.Enemy

import Controller.DrawingManager
import Model.Grid.{Grid, Tile}

class EnemyImpl(enemytype: EnemyType, grid: Grid) extends Enemy {

    //val path = grid.getGrid.iterator.indexWhere(n => n = 1)
    val startTile = grid.getGrid(1)(0)
    var actualTile : Tile = startTile
    var x = startTile.x
    var y = startTile.y
    var health: Int = enemytype.health
    val speed: Int = enemytype.speed
    var alive: Boolean = false

  override def draw(): Unit = {
    DrawingManager.drawTile(x.toDouble, y.toDouble, Easy.color)
    DrawingManager.print()
  }

  override def spawn(): Unit = {
    this.alive = true
  }

  override def move(t: Tile): Unit = ???

  override def currentTile(): Tile = {
    grid.getGrid(actualTile.xPlace)(actualTile.yPlace)
  }

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
