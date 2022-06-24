package Model.Grid

import Configuration.DefaultConfig.{TILE_HEIGHT_PX, TILE_WIDTH_PX}
import Model.Grid.Tiles.TileType
import scalafx.scene.paint.Color


trait Tile {

  /**
   * Method to get the drawing information about a tile
   *
   * @return a triplet composed by the color and the coordinates
   */
  def getDrawingInfo: (Color, Int, Int)

  /**
   * Method to get the x drawing coordinate (in pixel) of the tile
   *
   * @return the x coordinate of the tile
   */
  def xPlace: Int

  /**
   * Method to get the y drawing coordinate (in pixel) of the tile
   *
   * @return the y coordinate of the tile
   */
  def yPlace: Int

  /**
   * Method to get the x coordinate of the tile
   *
   * @return x coordinate of the tile
   */
  def x: Int

  /**
   * Method to get the y coordinate of the tile
   *
   * @return y coordinate of the tile
   */
  def y: Int

  /**
   * Method to get the type of the tile
   *
   * @return type of the tile
   */
  def tType: TileType
}


object Tile {

  /**
   * This class represent a single Tile
   *
   * @param x     position in the grid
   * @param y     position in the grid
   * @param tType tile type of the tile
   */
  sealed private case class TileImpl(x: Int, y: Int, tType: TileType) extends Tile {

    def getDrawingInfo: (Color, Int, Int) = (tType.col, x, y)

    def xPlace: Int = x / TILE_HEIGHT_PX

    def yPlace: Int = y / TILE_WIDTH_PX

  }

  def apply(x: Int, y: Int, tType: TileType): Tile = TileImpl(x, y, tType)
}
