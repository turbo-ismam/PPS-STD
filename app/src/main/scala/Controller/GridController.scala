package Controller

import Model.Grid.{Grid, Tile}
import scalafx.scene.paint.Color

import scala.collection.mutable.ArrayBuffer

/**
 * Controller of the game map
 * This class handle the grid
 * @param difficulty difficulty of the game
 */
class GridController(difficulty: Int) {

  private val _gameMap: Grid = new Grid(difficulty)

  def getTile(x: Int, y: Int): Tile = _gameMap.tile(x,y)

  def gameGrid: Array[Array[Tile]] = _gameMap.grid

  def isTileBuildable(x: Int, y: Int): Boolean = _gameMap.tile(x,y).tileType.buildable

  def drawingInfo: ArrayBuffer[(Color, Int, Int)] = _gameMap.gridDrawingInfo
}

