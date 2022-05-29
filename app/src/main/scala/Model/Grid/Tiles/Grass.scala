package Model.Grid.Tiles

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Green

class Grass extends TileType{

  override def buildable: Boolean = true

  override def tileType: TileTypes.TileType = TileTypes.Grass

  override def col: Color = Green
}
