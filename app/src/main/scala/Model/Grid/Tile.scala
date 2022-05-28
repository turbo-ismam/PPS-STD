package Model.Grid

import Controller.DrawingManager
import Model.Grid.Tiles.TileType

class Tile(val x: Int, val y: Int, val tType: TileType){

  def tileType: TileType = tType

  def draw(): Unit = {
    DrawingManager.drawTile(x, y, tileType.col)
    DrawingManager.print()
  }

  def xPlace: Int = x / 64

  def yPlace: Int = y / 64

}
