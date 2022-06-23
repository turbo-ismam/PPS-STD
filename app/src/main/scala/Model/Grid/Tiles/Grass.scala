package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Green

/**
 * Base tile type class that represent a Grass Tile
 * In this type of tile the player can place towers
 */
sealed class Grass extends TileType {

  override def buildable: Boolean = true

  override def tileType: TileTypes.TileType = TileTypes.Grass

  override def col: Color = Green
}
