package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black

/**
 * Base tile type class that represent nothing
 * This type of tile should be never used
 * If a grid contain this tile, there are map generation issues, for more detail check the grid class
 */
sealed class Nothing extends TileType {

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.Nothing

  override def col: Color = Black
}
