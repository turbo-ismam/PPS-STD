package Model.Grid

import Configuration.DefaultConfig.{TILE_HEIGHT_PX, TILE_WIDTH_PX}
import Model.Grid.Tiles.TileType
import scalafx.scene.paint.Color

/**
 * This class represent a single Tile
 *
 * @param x     position in the grid
 * @param y     position in the grid
 * @param tType tile type of the tile
 */
sealed class Tile private(val x: Int, val y: Int, val tType: TileType) {

  def tileType: TileType = tType

  def getDrawingInfo: (Color, Int, Int) = (tType.col, x, y)

  def xPlace: Int = x / TILE_HEIGHT_PX

  def yPlace: Int = y / TILE_WIDTH_PX

}

object Tile {

  def apply(x: Int, y: Int, tType: TileType): Tile = new Tile(x, y, tType)
}
