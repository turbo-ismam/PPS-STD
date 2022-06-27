package Model.Enemy

import Configuration.DefaultConfig
import Utility.Utils
import scalafx.scene.image.Image

object Medium extends EnemyType {

  def speed: Int = 50

  def health: Int = 200

  def damage: Int = 5

  def image: Image = Utils.getImageFromResource(DefaultConfig.MEDIUM_ENEMY_IMAGE)

  def apply: EnemyType = Easy
}
