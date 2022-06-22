package Model.Grid

import Configuration.DefaultConfig.{TILE_END_POSITION_ID, TILE_HEIGHT_PX, TILE_START_POSITION_ID, TILE_WIDTH_PX}
import Logger.LogHelper
import Model.Grid.Tiles.{TileType, TileTypes}
import Model.Grid.PathMaker.{customPath, hardPath, normalPath, simplePath}
import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer

sealed class Grid(difficulty: Int) extends LogHelper {

  private val _grid: Array[Array[Tile]] = makeGrid()

  private def makeGrid(): Array[Array[Tile]] = {

    val rawGrid: Array[Array[Int]] = difficulty match {
      case 1 => PathMaker.execute(simplePath)
      case 2 => PathMaker.execute(normalPath)
      case 3 => PathMaker.execute(hardPath)
      case 0 => PathMaker.execute(customPath)
    }

    if (!pathValidator(rawGrid)) System.exit(1)

    generateTileGrid(rawGrid)
  }

  private def generateTileGrid(rawGrid: Array[Array[Int]]): Array[Array[Tile]] = {

    val arrayOfTile = Array.ofDim[Tile](rawGrid.length, rawGrid(0).length)

    for (y <- rawGrid.indices) {
      for (x <- rawGrid(y).indices) {
        rawGrid(y)(x) match {
          case 0 => arrayOfTile(y)(x) = new Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.Grass))
          case 1 => arrayOfTile(y)(x) = new Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.Path))
          case 2 => arrayOfTile(y)(x) = new Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.StartTile))
          case 3 => arrayOfTile(y)(x) = new Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.EndTile))
          case _ => arrayOfTile(y)(x) = new Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.Nothing))
        }
      }
    }
    arrayOfTile
  }

  private def pathValidator(path: Array[Array[Int]]): Boolean = {
    if (containSingleStartOrEnd(path, TILE_START_POSITION_ID) &&
      containSingleStartOrEnd(path, TILE_END_POSITION_ID)) true else false
  }

  private def containSingleStartOrEnd(path: Array[Array[Int]], position: Int): Boolean = {
    path.flatMap(_.toSeq).groupBy(identity).view.mapValues(_.length)(position) match {
      case 1 => true
      case _ => false
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
