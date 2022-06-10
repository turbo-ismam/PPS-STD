package Model.Enemy

import scalafx.scene.paint.Color

trait EnemyType {
  //def text:
  def speed: Int
  def health: Int
  def width: Int = 64
  def height: Int = 64
  def color: Color
}

object Easy extends EnemyType {
  //val t = null
  //def text = t
  def speed: Int = 100
  def health: Int = 100
  def color: Color = Color.Red
}

object Medium extends EnemyType {
  //val t = null
  //def text = t
  def speed: Int = 150
  def health: Int = 200
  def color: Color = Color.DarkOrange
}

object Hard extends EnemyType {
  //val t = null
  //def text = t
  def speed: Int = 50
  def health: Int = 150
  def color: Color = Color.Purple
}
