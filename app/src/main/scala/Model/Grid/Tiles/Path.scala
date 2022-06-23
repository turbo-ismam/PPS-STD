package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Grey

/**
 * Base tile type class that represent a Path Tile
 * In this type of path the enemy can walk to reach the end of the grid, player can't place here towers.
 */
sealed class Path extends TileType {

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.Path

  override def col: Color = Grey
}
