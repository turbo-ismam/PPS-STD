package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black

/**
 * Base tile type class that represent the start point of path
 * This type of tile represent the spawn point of enemies, all kind of enemy spawn here and then they can go to end.
 * Player can't build tower here and a grid can contain only one tile of this type, if it doesn't contain or contain
 * more than one of these type of tile, there are some grid generation issues, for more info check grid class.
 */
sealed class StartTile extends TileType {

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.StartTile

  override def col: Color = Black
}
