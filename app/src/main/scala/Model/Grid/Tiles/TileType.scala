package Model.Grid.Tiles

import Configuration.DefaultConfig.{TILE_HEIGHT_PX, TILE_WIDTH_PX}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{Black, Green, Grey}

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
   * Base tile type class that represent the end point of the path
   * The enemies, if arrive alive here, can despawn and damage the player health.
   * Player can't build tower here and a grid can contain only one tile of this type, if it doesn't contain or contain
   * more than one of these type of tile, there are some grid generation issues, for more info check grid class.
   */
  sealed private case class EndTile() extends TileType {

    override def buildable: Boolean = false

    override def tileType: TileTypes.TileType = TileTypes.EndTile

    override def col: Color = Black
  }

  /**
   * Base tile type class that represent a Grass Tile
   * In this type of tile the player can place towers
   */
  sealed private case class Grass() extends TileType {

    override def buildable: Boolean = true

    override def tileType: TileTypes.TileType = TileTypes.Grass

    override def col: Color = Green
  }

  /**
   * Base tile type class that represent nothing
   * This type of tile should be never used
   * If a grid contain this tile, there are map generation issues, for more detail check the grid class
   */
  sealed private case class Nothing() extends TileType {

    override def buildable: Boolean = false

    override def tileType: TileTypes.TileType = TileTypes.Nothing

    override def col: Color = Black
  }

  /**
   * Base tile type class that represent a Path Tile
   * In this type of path the enemy can walk to reach the end of the grid, player can't place here towers.
   */
  sealed private case class Path() extends TileType {

    override def buildable: Boolean = false

    override def tileType: TileTypes.TileType = TileTypes.Path

    override def col: Color = Grey
  }

  /**
   * Base tile type class that represent the start point of path
   * This type of tile represent the spawn point of enemies, all kind of enemy spawn here and then they can go to end.
   * Player can't build tower here and a grid can contain only one tile of this type, if it doesn't contain or contain
   * more than one of these type of tile, there are some grid generation issues, for more info check grid class.
   */
  sealed private case class StartTile() extends TileType {

    override def buildable: Boolean = false

    override def tileType: TileTypes.TileType = TileTypes.StartTile

    override def col: Color = Black
  }


  /**
   * Method to generate a Tile type, this method is used to build all sort of tile types
   *
   * @param kind tile type of the tile
   * @return new instance of requested tile
   */
  def apply(kind: TileTypes.TileType): TileType = kind match {
    case TileTypes.Grass => Grass()
    case TileTypes.Path => Path()
    case TileTypes.StartTile => StartTile()
    case TileTypes.EndTile => EndTile()
    case TileTypes.Nothing => Nothing()
  }

}