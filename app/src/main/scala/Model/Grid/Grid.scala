package Model.Grid

import Configuration.DefaultConfig.{TILE_END_POSITION_ID, TILE_HEIGHT_PX, TILE_START_POSITION_ID, TILE_WIDTH_PX}
import Logger.LogHelper
import Model.Grid.Tiles.{Tile, TileType, TileTypes}

/**
 * Interface of the Grid Model
 */
trait Grid {

  /**
   * Method to get the grid
   *
   * @return an array of array of tile that represent the grid
   */
  def grid: Array[Array[Tile]]

}

object Grid {

  sealed private case class GridImpl(difficulty: Int) extends Grid with LogHelper {

    private val _grid: Array[Array[Tile]] = makeGrid()

    private def makeGrid(): Array[Array[Tile]] = {

      val pathMaker = PathMaker()

      val rawGrid: Array[Array[Int]] = difficulty match {
        case 1 => pathMaker.execute(pathMaker.simplePath)
        case 2 => pathMaker.execute(pathMaker.normalPath)
        case 3 => pathMaker.execute(pathMaker.hardPath)
        case 0 => pathMaker.execute(pathMaker.customPath)
      }

      generateTileGrid(rawGrid)
    }

    private def generateTileGrid(rawGrid: Array[Array[Int]]): Array[Array[Tile]] = {

      val arrayOfTile = Array.ofDim[Tile](rawGrid.length, rawGrid(0).length)

      for (y <- rawGrid.indices) {
        for (x <- rawGrid(y).indices) {
          rawGrid(y)(x) match {
            case 0 => arrayOfTile(y)(x) = Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.Grass))
            case 1 => arrayOfTile(y)(x) = Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.Path))
            case 2 => arrayOfTile(y)(x) = Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.StartTile))
            case 3 => arrayOfTile(y)(x) = Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.EndTile))
            case _ => arrayOfTile(y)(x) = Tile(x * TILE_HEIGHT_PX, y * TILE_WIDTH_PX, TileType(TileTypes.Nothing))
          }
        }
      }
      arrayOfTile
    }


    override def grid: Array[Array[Tile]] = _grid

  }

  def apply(difficulty: Int): Grid = GridImpl(difficulty)
}
