package Model.Enemy

import Controller.DrawingManager
import Model.Grid.{Grid, Tile}

import scala.util.control.Breaks.break

class EnemyImpl(enemytype: EnemyType, grid: Grid) extends Enemy {

    val startPath = findPath(grid)
    val startTile = grid.getGrid(1)(0)
    var actualTile : Tile = startTile
    var x = startPath(0)*64
    var y = startPath(1)*64
    var health: Int = enemytype.health
    val speed: Int = enemytype.speed
    var alive: Boolean = false

  def findPath(grid: Grid): Array[Int] = {
    var x = -1
    var y = 0
    for(row <- grid.getGrid) {
      x = row.indexWhere(p => p.yPlace == 1)
      if(x != -1){
        println(x)
        return Array(x,y)
      }
      y = y + 1
    }
    Array(7,7)
  }

  override def draw(): Unit = {
    DrawingManager.drawTile(x.toDouble, y.toDouble, Easy.color)
    DrawingManager.print()
    println(x)
    println(y)
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
