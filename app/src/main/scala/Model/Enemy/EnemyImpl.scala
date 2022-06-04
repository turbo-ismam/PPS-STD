package Model.Enemy

import Controller.DrawingManager
import Model.Grid.{Grid, Tile}


class EnemyImpl(enemytype: EnemyType, grid: Grid) extends Enemy {

    var actualTile : Tile = grid.getGrid(findFirstTile(grid,-1,0)(0))(findFirstTile(grid,-1,0)(1))
    var dirMultp = (0, 0)
    var health: Int = enemytype.health
    val speed: Int = enemytype.speed
    var alive: Boolean = false

  def findFirstTile(grid: Grid, x: Int, y: Int): Array[Int] = x match {
    case -1 => findFirstTile(grid,grid.getGrid(y).indexWhere(p => p.yPlace == 1),y+1)
    case _ => Array(x,y-1)
  }

  override def draw(): Unit = {
    DrawingManager.drawTile(this.currentTile().xPlace.toDouble*64, this.currentTile().yPlace.toDouble*64, Easy.color)
    DrawingManager.print()
  }

  override def spawn(): Unit = {
    this.alive = true
  }

  //Finds the next direction.
  override def move(t: Tile): Unit = {
    //All the surrounding tiles.
    var u = t
    var l = t
    if (t.yPlace != 0){
      u = grid.getGrid(t.xPlace)(t.yPlace-1)
    }
    else{
      u = grid.getGrid(t.xPlace)(t.yPlace)
    }

    val d = grid.getGrid(t.xPlace)(t.yPlace+1)
    val r = grid.getGrid(t.xPlace+1)(t.yPlace)

    if (t.xPlace != 0){
      l = grid.getGrid(t.xPlace-1)(t.yPlace)
    }
    else {
      l = grid.getGrid(t.xPlace)(t.yPlace)
    }


    //Enemy cant turn 180 degrees around so current value of dirMultp cant be opposite.
    if (u.tType.tileType == t.tType.tileType && dirMultp != (0, 1)) {
      this.actualTile = u
      println("upper")
    } else if (d.tType.tileType == t.tType.tileType && dirMultp != (0, -1)) {
      this.actualTile = d
      println("bottom")
    } else if (r.tType.tileType == t.tType.tileType && dirMultp != (-1, 0)) {
      this.actualTile = r
      println("right")
    } else if (l.tType.tileType == t.tType.tileType && dirMultp != (1, 0)) {
      this.actualTile = l
      println("left")
    }
  }

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
