package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black

/**
 * Base tile type class that represent the end point of the path
 * The enemies, if arrive alive here, can despawn and damage the player health.
 * Player can't build tower here and a grid can contain only one tile of this type, if it doesn't contain or contain
 * more than one of these type of tile, there are some grid generation issues, for more info check grid class.
 */
sealed class EndTile extends TileType {

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.EndTile

  override def col: Color = Black
}
