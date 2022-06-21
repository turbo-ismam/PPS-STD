package Model.Grid.Tiles

import Configuration.DefaultConfig.{TILE_HEIGHT_PX, TILE_WIDTH_PX}
import scalafx.scene.paint.Color

/**
 * Interface that represent a single tile
 */
trait TileType {

  def buildable: Boolean

  def col: Color

  def tileType: TileTypes.TileType

  def width: Int = TILE_WIDTH_PX

  def height: Int = TILE_HEIGHT_PX
}

object TileType {

  /**
   * Method to generate a Tile type, this method is used to build all sort of tile types
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