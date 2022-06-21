package Model.Enemy

import Controller.GridController
import Logger.LogHelper
import Model.Grid.Tile
import Model.Grid.Tiles.{TileType, TileTypes}


class EnemyImpl(enemyType: EnemyType, gridController: GridController) extends Enemy with LogHelper {

  var actualTile: Tile = findFirstTile(gridController)
  var dirMulti: (Int, Int) = (0, 0)
  var health: Int = enemyType.health
  val speed: Int = enemyType.speed
  var alive: Boolean = false
  var tick: Int = 0
  var x = actualTile.x.toDouble
  var y = actualTile.y.toDouble
  var dir_check: Boolean = false
  var dir_val_check = 0

  def findFirstTile(gridController: GridController): Tile = {
    gridController.tileStartOrEnd(TileTypes.StartTile) match {
      case Some(tile) => tile
      case None => new Tile(0,0,TileType(TileTypes.StartTile))
    }
  }

  override def update(delta: Double) = {
    this.death()
    this.move(delta)
  }

  override def getX(): Double = {
    this.x
  }

  override def getY(): Double = {
    this.y
  }

  override def getType(): EnemyType = {
    this.enemyType
  }

  override def spawn(): Unit = {
    this.alive = true
  }

  //Finds the next direction.
  override def move(delta:Double): Unit = {
    //All the surrounding tiles.
    val t = this.actualTile
    var u = gridController.gameGrid(0)(0)
    var l = gridController.gameGrid(0)(0)
    var d = gridController.gameGrid(0)(0)
    var r = gridController.gameGrid(0)(0)
    if (t.yPlace != 0) {
      u = gridController.gameGrid(t.yPlace - 1)(t.xPlace)
    }
    else {
      u = gridController.gameGrid(t.yPlace)(t.xPlace)
    }

    if (t.yPlace != 19) {
      d = gridController.gameGrid(t.yPlace + 1)(t.xPlace)
    }
    else {
      d = gridController.gameGrid(t.yPlace)(t.xPlace)
    }

    if (t.xPlace != 19) {
      r = gridController.gameGrid(t.yPlace)(t.xPlace + 1)
    }
    else {
      r = gridController.gameGrid(t.yPlace)(t.xPlace)
    }

    if (t.xPlace != 0) {
      l = gridController.gameGrid(t.yPlace)(t.xPlace - 1)
    }
    else {
      l = gridController.gameGrid(t.yPlace)(t.xPlace)
    }


    //Enemy cant turn 180 degrees around so current value of dirMulti cant be opposite.
    if ((u.tType.tileType == TileTypes.EndTile || u.tType.tileType == TileTypes.Path || u.tType.tileType == t.tType.tileType) && dirMulti != (0, 1) && (dir_val_check == 0 || dir_val_check == 1)) {
      if(!this.dir_check){
        dirMulti = (0, -1)
        this.dir_check = true
      }
      if (x > u.x - 10 && x < u.x + 10 && y > u.y - 10 && y < u.y + 10) {
        x = u.x
        y = u.y
        this.actualTile = gridController.gameGrid(u.yPlace)(u.xPlace)
        this.dir_check = false
        this.dir_val_check = 0
      }
      else{
        x +=  delta*speed*dirMulti._1
        y +=  delta*speed*dirMulti._2
        this.dir_val_check = 1
      }

    } else if ((d.tType.tileType == TileTypes.EndTile || d.tType.tileType == TileTypes.Path || d.tType.tileType == t.tType.tileType) && dirMulti != (0, -1) && (dir_val_check == 0 || dir_val_check == 2)) {
      if(!this.dir_check){
        dirMulti = (0, 1)
        this.dir_check = true
      }
      if (x > d.x - 10 && x < d.x + 10 && y > d.y - 10 && y < d.y + 10) {
        x = d.x
        y = d.y
        this.actualTile = gridController.gameGrid(d.yPlace)(d.xPlace)
        this.dir_check = false
        this.dir_val_check = 0
      }
      else{
        x +=  delta*speed*dirMulti._1
        y +=  delta*speed*dirMulti._2
        this.dir_val_check = 2
      }

    } else if ((r.tType.tileType == TileTypes.EndTile || r.tType.tileType == TileTypes.Path || r.tType.tileType == t.tType.tileType) && dirMulti != (-1, 0) && (dir_val_check == 0 || dir_val_check == 3)) {
      if(!this.dir_check){
        dirMulti = (1, 0)
        this.dir_check = true
      }
      if (x > r.x - 10 && x < r.x + 10 && y > r.y - 10 && y < r.y + 10) {
        x = r.x
        y = r.y
        this.actualTile = gridController.gameGrid(r.yPlace)(r.xPlace)
        this.dir_check = false
        this.dir_val_check = 0
      }
      else{
        x +=  delta*speed*dirMulti._1
        y +=  delta*speed*dirMulti._2
        this.dir_val_check = 3
      }

    } else if ((l.tType.tileType == TileTypes.EndTile || l.tType.tileType == TileTypes.Path || l.tType.tileType == t.tType.tileType) && dirMulti != (1, 0) && (dir_val_check == 0 || dir_val_check == 4)) {
      if(!this.dir_check){
        dirMulti = (-1, 0)
        this.dir_check = true
      }
      if (x > l.x - 10 && x < l.x + 10 && y > l.y - 10 && y < l.y + 10) {
        x = l.x
        y = l.y
        this.actualTile = gridController.gameGrid(l.yPlace)(l.xPlace)
        this.dir_check = false
        this.dir_val_check = 0
      }
      else{
        x +=  delta*speed*dirMulti._1
        y +=  delta*speed*dirMulti._2
        this.dir_val_check = 4
      }
    }
  }

  override def enemyCurrentPosition(): Tile = {
    gridController.gameGrid(actualTile.yPlace)(actualTile.xPlace)
  }

  override def takeDamage(i: Int): Unit = {
    this.health -= i
    if (this.health <= 0) {
      this.death()
    }
  }

  override def isAlive(): Boolean = {
    alive
  }

  override def death(): Unit = {
    if (this.health <= 0) {
      this.alive = false
    }
  }

  override def destroy(): Unit = {
    this.health = -1
    this.death()
  }
}
