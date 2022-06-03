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
    case -1 => findFirstTile(grid,grid.getGrid(y).indexWhere(p => p.yPlace == 1),y+1)
    case _ => Array(x,y-1)
  }

  def update(delta: Double) = {
    this.death()
    if (tick >= speed) {
      this.move()
      tick = 0
    }
    else tick += 1
  }

  override def draw(): Unit = {
    DrawingManager.drawTile(this.currentTile().x.toDouble, this.currentTile().y.toDouble, Easy.color)
    DrawingManager.print()
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
    if (t.yPlace.intValue() != 0){
      u = grid.getGrid(t.yPlace-1)(t.xPlace)
    }
    else{
      u = grid.getGrid(t.yPlace)(t.xPlace)
    }

    val d = grid.getGrid(t.xPlace)(t.yPlace+1)
    val r = grid.getGrid(t.xPlace+1)(t.yPlace)

    if (t.xPlace != 0){
      l = grid.getGrid(t.xPlace-1)(t.yPlace)
    }
    else {
      l = grid.getGrid(t.xPlace)(t.yPlace)
    }

    println(t.xPlace)
    println(t.yPlace)
    println(r.xPlace)
    println(r.yPlace)
    println(u.tType.tileType)
    println(d.tType.tileType)
    println(r.tType.tileType)
    println(l.tType.tileType)


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
