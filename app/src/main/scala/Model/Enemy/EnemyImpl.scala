package Model.Enemy

import Controller.DrawingManager
import Model.Grid.{Grid, Tile}


class EnemyImpl(enemytype: EnemyType, grid: Grid) extends Enemy {

    var actualTile : Tile = grid.getGrid(findFirstTile(grid,-1,0)(0))(findFirstTile(grid,-1,0)(1))
    var dirMultp = (0, 0)
    var health: Int = enemytype.health
    val speed: Int = enemytype.speed
    var alive: Boolean = false
    var tick: Int = 0

  def findFirstTile(grid: Grid, x: Int, y: Int): Array[Int] = x match {
    case -1 => findFirstTile(grid,grid.getGrid(y).indexWhere(p => p.xPlace == 1),y+1)
    case _ => Array(y-1,x)
  }

  def update(delta: Double) = {
    this.death()
    if (tick >= speed) {
      this.move()
      tick = 0
    }
    else{
      tick += 1
    }
  }

  override def getType(): EnemyType = {
    this.enemytype
  }

  override def spawn(): Unit = {
    this.alive = true
  }

  //Finds the next direction.
  override def move(): Unit = {
    //All the surrounding tiles.
    val t = this.currentTile()
    var u = grid.getGrid(0)(0)
    var l = grid.getGrid(0)(0)
    var d = grid.getGrid(0)(0)
    var r = grid.getGrid(0)(0)
    if (t.yPlace != 0){
      u = grid.getGrid(t.yPlace-1)(t.xPlace)
    }
    else{
      u = grid.getGrid(t.yPlace)(t.xPlace)
    }

    if(t.yPlace != 19){
      d = grid.getGrid(t.yPlace+1)(t.xPlace)
    }
    else{
      d = grid.getGrid(t.yPlace)(t.xPlace)
    }

    if(t.xPlace != 14){
      r = grid.getGrid(t.yPlace)(t.xPlace+1)
    }
    else{
      r = grid.getGrid(t.yPlace)(t.xPlace)
    }

    if (t.xPlace != 0){
      l = grid.getGrid(t.yPlace)(t.xPlace-1)
    }
    else {
      l = grid.getGrid(t.yPlace)(t.xPlace)
    }


    //Enemy cant turn 180 degrees around so current value of dirMultp cant be opposite.
    if (u.tType.tileType == t.tType.tileType && dirMultp != (0, 1)) {
      this.actualTile = grid.getGrid(u.xPlace)(u.yPlace)
      dirMultp = (0, -1)
      println("upper")

    } else if (d.tType.tileType == t.tType.tileType && dirMultp != (0, -1)) {
      this.actualTile = grid.getGrid(d.xPlace)(d.yPlace)
      dirMultp = (0, 1)
      println("bottom")
    } else if (r.tType.tileType == t.tType.tileType && dirMultp != (-1, 0)) {
      actualTile = grid.getGrid(r.xPlace)(r.yPlace)
      dirMultp = (1, 0)
      println("right")
    } else if (l.tType.tileType == t.tType.tileType && dirMultp != (1, 0)) {
      this.actualTile = grid.getGrid(l.xPlace)(l.yPlace)
      dirMultp = (-1, 0)
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
