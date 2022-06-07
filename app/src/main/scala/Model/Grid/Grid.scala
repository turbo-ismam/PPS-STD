package Model.Grid

import Model.Grid.Tiles.{TileType, TileTypes}
import Model.Grid.PathMaker.simplePath
import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer

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

  def getGridDrawingInfo: ArrayBuffer[(Color, Int, Int)] = {
    //grid.foreach(_.foreach(_.getDrawingInfo()))
    val buffer: ArrayBuffer[(Color, Int, Int)] = new ArrayBuffer()
    grid.foreach(_.foreach(tile => buffer.addOne(tile.getDrawingInfo)))
    buffer
  }

  def getTile(x: Int, y: Int): Tile = grid(y)(x)

  def getGrid: Array[Array[Tile]] = grid

  private def createSimpleGrid(): Array[Array[Tile]] = {
    val arr = PathMaker.execute(simplePath)
    val a = Array.ofDim[Tile](arr.length, arr(0).length)
    for (y <- arr.indices) {
      for (x <- arr(y).indices) {
        arr(y)(x) match {
          case 0 => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.Grass))
          case 1 => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.Path))
          case 2 => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.StartTile))
          case 3 => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.EndTile))
          case _ => a(y)(x) = new Tile(x * 64, y * 64, TileType(TileTypes.Nothing))
        }
      }
    }
    a
  }

}
