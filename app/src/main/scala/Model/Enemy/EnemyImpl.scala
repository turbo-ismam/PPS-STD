package Model.Enemy

import Logger.LogHelper
import Model.Grid.{GridController, Tile}
import Model.Grid.Tiles.{TileType, TileTypes}


class EnemyImpl(enemyType: EnemyType, gridController: GridController) extends Enemy with LogHelper {

  private var actualTile: Tile = findFirstTile(gridController)
  private var dirMulti: (Int, Int) = (0, 0)
  private var health: Int = enemyType.health
  private val speed: Int = enemyType.speed
  private var alive: Boolean = false
  private var x = actualTile.x.toDouble
  private var y = actualTile.y.toDouble
  private var dir_check: Boolean = false
  private var dir_val_check = 0

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

  //Finds the next direction.
  override def move(delta:Double): Unit = {
    //All the surrounding tiles.
    val t = this.actualTile
    var up = gridController.gameGrid(0)(0)
    var down = gridController.gameGrid(0)(0)
    var right = gridController.gameGrid(0)(0)
    var left = gridController.gameGrid(0)(0)
    if (t.yPlace != 0) {
      up = gridController.gameGrid(t.yPlace - 1)(t.xPlace)
    }
    else {
      up = gridController.gameGrid(t.yPlace)(t.xPlace)
    }

    if (t.yPlace != 19) {
      down = gridController.gameGrid(t.yPlace + 1)(t.xPlace)
    }
    else {
      down = gridController.gameGrid(t.yPlace)(t.xPlace)
    }

    if (t.xPlace != 19) {
      right = gridController.gameGrid(t.yPlace)(t.xPlace + 1)
    }
    else {
      right = gridController.gameGrid(t.yPlace)(t.xPlace)
    }

    if (t.xPlace != 0) {
      left = gridController.gameGrid(t.yPlace)(t.xPlace - 1)
    }
    else {
      left = gridController.gameGrid(t.yPlace)(t.xPlace)
    }


    dir_val_check match {
      case 0 | 1 if (up.tType.tileType == TileTypes.EndTile || up.tType.tileType == TileTypes.Path || up.tType.tileType == t.tType.tileType) && dirMulti != (0, 1) => moveToPosition(up,"up",delta)
      case 0 | 2 if (down.tType.tileType == TileTypes.EndTile || down.tType.tileType == TileTypes.Path || down.tType.tileType == t.tType.tileType) && dirMulti != (0, -1) => moveToPosition(down,"down",delta)
      case 0 | 3 if (right.tType.tileType == TileTypes.EndTile || right.tType.tileType == TileTypes.Path || right.tType.tileType == t.tType.tileType) && dirMulti != (-1, 0) => moveToPosition(right,"right",delta)
      case 0 | 4 if (left.tType.tileType == TileTypes.EndTile || left.tType.tileType == TileTypes.Path || left.tType.tileType == t.tType.tileType) && dirMulti != (1, 0) => moveToPosition(left,"left",delta)
    }
  }

  def moveToPosition(tile: Tile, direction: String, delta: Double): Unit = {
    if(!this.dir_check){
      direction match {
        case "up" => dirMulti = (0, -1)
        case "down" => dirMulti = (0, 1)
        case "right" => dirMulti = (1, 0)
        case "left" => dirMulti = (-1, 0)
      }
      this.dir_check = true
    }
    if (x > tile.x - 10 && x < tile.x + 10 && y > tile.y - 10 && y < tile.y + 10) {
      x = tile.x
      y = tile.y
      this.actualTile = gridController.gameGrid(tile.yPlace)(tile.xPlace)
      this.dir_check = false
      this.dir_val_check = 0
    }
    else{
      x +=  delta*speed*dirMulti._1
      y +=  delta*speed*dirMulti._2
      direction match {
        case "up" => this.dir_val_check = 1
        case "down" => this.dir_val_check = 2
        case "right" => this.dir_val_check = 3
        case "left" => this.dir_val_check = 4
      }
    }
  }

}

object EnemyImpl{
  def apply(enemyType: EnemyType, gridController: GridController): EnemyImpl = {
    val enemyImpl: EnemyImpl = new EnemyImpl(enemyType, gridController)
    enemyImpl
  }
}
