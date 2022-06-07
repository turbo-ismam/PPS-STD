package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black

class EndTile extends TileType{

  override def buildable: Boolean = false

  override def tileType: TileTypes.TileType = TileTypes.EndTile

  override def col: Color = Black
}
