package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black

sealed class Nothing extends TileType{

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.Nothing

  override def col: Color = Black
}
