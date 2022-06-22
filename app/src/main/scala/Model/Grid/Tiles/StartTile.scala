package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black

sealed class StartTile extends TileType{

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.StartTile

  override def col: Color = Black
}
