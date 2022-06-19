package Model.Grid

import Model.Grid.Tiles.TileType
import scalafx.scene.paint.Color

class Tile(val x: Int, val y: Int, val tType: TileType) {

  def tileType: TileType = tType

  def getDrawingInfo: (Color, Int, Int) = (tType.col, x, y)

  def xPlace: Int = x / 64

  def yPlace: Int = y / 64

}

object Tile {

  def apply(x: Int, y: Int, tType: TileType): Tile = new Tile(x,y,tType)
}
