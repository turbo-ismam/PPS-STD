package Model.Grid

import Configuration.DefaultConfig.{DIFFICULTY_SELECTION_ERROR, GRID_CREATION_ERROR}
import Logger.LogHelper
import Model.Grid.Tiles.{TileType, TileTypes}
import Model.Grid.PathMaker.{customPath, hardPath, normalPath, simplePath}
import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer

class Grid(difficulty: Int) extends LogHelper {

  private val _grid: Array[Array[Tile]] = createGrid()

  private def createGrid(): Array[Array[Tile]] = {
    difficulty match {
      case 1 => makeGrid(1)
      case 2 => makeGrid(2)
      case 3 => makeGrid(3)
      case 0 => makeGrid(0)
      case _ => logger.error(DIFFICULTY_SELECTION_ERROR)
        makeGrid(1)
    }
  }

  private def makeGrid(difficulty: Int): Array[Array[Tile]] = {

    var arr: Option[Array[Array[Int]]] = None

    difficulty match {
      case 1 => arr = Some(PathMaker.execute(simplePath))
      case 2 => arr = Some(PathMaker.execute(normalPath))
      case 3 => arr = Some(PathMaker.execute(hardPath))
      case 0 => arr = Some(PathMaker.execute(customPath))
    }

    arr match {
      case None => logger.error(GRID_CREATION_ERROR)
        null
      case Some(value) => val a = Array.ofDim[Tile](value.length, value(0).length)
        for (y <- value.indices) {
          for (x <- value(y).indices) {
            value(y)(x) match {
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

  def gridDrawingInfo: ArrayBuffer[(Color, Int, Int)] = {
    val buffer: ArrayBuffer[(Color, Int, Int)] = new ArrayBuffer()
    _grid.foreach(_.foreach(tile => buffer.addOne(tile.getDrawingInfo)))
    buffer
  }

  def tile(x: Int, y: Int): Tile = _grid(y)(x)

  def grid: Array[Array[Tile]] = _grid

  def tileStartOrEnd(filter: TileTypes.TileType): Option[Tile] = {
    filter match {
      case TileTypes.StartTile | TileTypes.EndTile =>
        _grid.foreach(y => y.foreach(x => if (x.tileType.tileType == filter) {
          return Some(new Tile(x.x, x.y, TileType(filter)))
        }))
        None
      case _ => None
    }
  }
}
