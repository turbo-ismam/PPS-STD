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

  private val gameMap: Grid = new Grid(difficulty)

  def getTile(x: Int, y: Int): Tile = gameMap.getTile(x,y)

  def getGameMap: Array[Array[Tile]] = gameMap.getGrid

  def isTileBuildable(x: Int, y: Int): Boolean = gameMap.getTile(x,y).tileType.buildable

  def getDrawingInfo: ArrayBuffer[(Color, Int, Int)] = gameMap.getGridDrawingInfo

}

