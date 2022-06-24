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
trait Tile {

  def getDrawingInfo: (Color, Int, Int)

  def xPlace: Int

  def yPlace: Int

  def x: Int

  def y: Int

  def tType: TileType
}


object Tile {

  sealed private case class TileImpl(x: Int, y: Int, tType: TileType) extends Tile {

    def getDrawingInfo: (Color, Int, Int) = (tType.col, x, y)

    def xPlace: Int = x / TILE_HEIGHT_PX

    def yPlace: Int = y / TILE_WIDTH_PX

  }

  def apply(x: Int, y: Int, tType: TileType): Tile = TileImpl(x, y, tType)
}
