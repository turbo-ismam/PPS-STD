package Model.Grid

import Model.Grid.Tiles.{TileType, TileTypes}
import Model.Grid.PathMaker.simplePath

class Grid(difficulty: Int) {

  private val grid: Array[Array[Tile]] = createGrid()

  private def createGrid(): Array[Array[Tile]] = {
    difficulty match {
      case 1 => createSimpleGrid()
      case 2 => ???
      case 3 => ???
      case _ => null
    }
  }

  def draw(): Unit = {
    grid.foreach(_.foreach(_.draw()))
  }

  def setTile(): Unit = ???

  def getTile(): Tile = ???

  def getGrid: Array[Array[Tile]] = grid

  private def createSimpleGrid(): Array[Array[Tile]] = {
    val arr = PathMaker.execute(simplePath)
    val a = Array.ofDim[Tile](arr.length, arr(0).length)
    for (y <- arr.indices) {
      for (x <- arr(y).indices) {
        arr(y)(x) match {
          case 0 => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.Grass))
          case 1 => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.Path))
          case _ => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.Nothing))
        }
      }
    }
    a
  }

}
