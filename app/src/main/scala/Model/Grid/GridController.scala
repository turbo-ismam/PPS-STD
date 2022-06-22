package Model.Grid

import Model.Grid.Tiles.TileTypes
import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer

/**
 * Controller of the game map
 * This class handle the grid
 *
 * @param difficulty difficulty of the game
 */
sealed class GridController private(difficulty: Int) {

  private val _gameMap: Grid = Grid(difficulty)

  /**
   * Method to get a specific tile by the given position (x,y)
   *
   * @param x raw of the tile in the grid
   * @param y column of the tile in the grid
   * @return the tile that correspond to the given raw and column
   */
  def getTile(x: Int, y: Int): Tile = _gameMap.tile(x, y)

  /**
   * Method to get the grid of the game
   *
   * @return the grid
   */
  def gameGrid: Array[Array[Tile]] = _gameMap.grid

  /**
   * Method to check if a tile is a buildable tile
   * A tile is buildable when it isn't a:
   * 1. Path tile
   * 2. Start Tile
   * 3. End Tile
   *
   * @param x raw of the tile in the grid
   * @param y column of the tile in the grid
   * @return true if is buildable, otherwise false
   */
  def isTileBuildable(x: Int, y: Int): Boolean = _gameMap.tile(x, y).tileType.buildable

  /**
   * Method to retrieve the drawing information about the grid
   *
   * @return a list that contain a triplet for each tile in the grid
   */
  def drawingInfo: ArrayBuffer[(Color, Int, Int)] = _gameMap.gridDrawingInfo

  /**
   * Method to retrieve an optional of the start or end tile
   *
   * @param filter can be start or end tile type
   * @return if exist, the start or end tile
   */
  def tileStartOrEnd(filter: TileTypes.TileType): Option[Tile] = _gameMap.tileStartOrEnd(filter)

}

object GridController {

  def apply(difficulty: Int): GridController = new GridController(difficulty)
}
