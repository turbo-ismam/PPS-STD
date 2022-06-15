package Model.Enemy

import Configuration.DefaultConfig
import Utility.Utils
import scalafx.scene.image.Image
import scalafx.scene.paint.Color

trait EnemyType {
  //def text:
  def speed: Int
  def health: Int
  def width: Int = 64
  def height: Int = 64
  def image: Image
}

object Easy extends EnemyType {
  //val t = null
  //def text = t
  def speed: Int = 100
  def health: Int = 100
  def image: Image = Utils.getImageFromResource(DefaultConfig.BASE_ENEMY_IMAGE)
}

object Medium extends EnemyType {
  //val t = null
  //def text = t
  def speed: Int = 50
  def health: Int = 200
  def image: Image = Utils.getImageFromResource(DefaultConfig.BASE_ENEMY_IMAGE)
}

object Hard extends EnemyType {
  //val t = null
  //def text = t
  def speed: Int = 150
  def health: Int = 150
  def image: Image = Utils.getImageFromResource(DefaultConfig.BASE_ENEMY_IMAGE)
}
