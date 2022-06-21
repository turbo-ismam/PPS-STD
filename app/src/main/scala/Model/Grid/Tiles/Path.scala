package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Grey

sealed class Path extends TileType{

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.Path

  override def col: Color = Grey
}
