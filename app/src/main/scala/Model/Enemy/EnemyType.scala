package Model.Enemy

import Configuration.DefaultConfig
import Utility.Utils
import scalafx.scene.image.Image

trait EnemyType {
  def speed: Int
  def health: Int
  def width: Int = 64
  def height: Int = 64
  def damage: Int
  def image: Image
}

object Easy extends EnemyType {

  def speed: Int = 100
  def health: Int = 100
  def damage: Int = 5
  def image: Image = Utils.getImageFromResource(DefaultConfig.BASE_ENEMY_IMAGE)
}

object Medium extends EnemyType {

  def speed: Int = 50
  def health: Int = 200
  def damage: Int = 5
  def image: Image = Utils.getImageFromResource(DefaultConfig.MEDIUM_ENEMY_IMAGE)
}

object Hard extends EnemyType {

  def speed: Int = 150
  def health: Int = 150
  def damage: Int = 10
  def image: Image = Utils.getImageFromResource(DefaultConfig.HARD_ENEMY_IMAGE)
}
