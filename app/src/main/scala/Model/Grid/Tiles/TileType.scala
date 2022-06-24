package Model.Grid.Tiles

import Configuration.DefaultConfig.{TILE_HEIGHT_PX, TILE_WIDTH_PX}
import scalafx.scene.paint.Color

/**
 * Interface that represent a single tile type
 */
trait TileType {

  /**
   * Method to check if the tile type is a buildable tile type
   * A TileType is buildable only if it is a Grass tile type
   *
   * @return true if is buildable, otherwise false
   */
  def buildable: Boolean

  /**
   * Method to get the color of the tile Type
   *
   * @return the color
   */
  def col: Color

  /**
   * Method to get the enumeration of the tile type
   *
   * @return the enumeration element
   */
  def tileType: TileTypes.TileType

  /**
   * Method to get the pixel width of the tile type
   *
   * @return the width of the tile type
   */
  def width: Int = TILE_WIDTH_PX

  /**
   * Method to get the pixel height of the tile type
   *
   * @return the height of the tile type
   */
  def height: Int = TILE_HEIGHT_PX
}

object TileType {

  /**
   * Method to generate a Tile type, this method is used to build all sort of tile types
   *
   * @param kind tile type of the tile
   * @return new instance of requested tile
   */
  def apply(kind: TileTypes.TileType): TileType = kind match {
    case TileTypes.Grass => new Grass()
    case TileTypes.Path => new Path()
    case TileTypes.StartTile => new StartTile()
    case TileTypes.EndTile => new EndTile()
    case TileTypes.Nothing => new Nothing()
  }

}