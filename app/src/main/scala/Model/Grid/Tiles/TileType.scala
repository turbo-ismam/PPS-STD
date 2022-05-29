package Model.Grid.Tiles

import scalafx.scene.paint.Color

trait TileType {

  def buildable: Boolean

  def col: Color

  def tileType: TileTypes.TileType

  def width: Int = 64

  def height: Int = 64
}

object TileType {

  def apply(kind: TileTypes.TileType): TileType = kind match {
    case TileTypes.Grass => new Grass()
    case TileTypes.Path => new Path()
    case TileTypes.Nothing => new Nothing()
  }

}