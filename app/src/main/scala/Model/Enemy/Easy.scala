package Model.Enemy

import Utility.Configuration.DefaultConfig
import Utility.Utils
import scalafx.scene.image.Image

object Easy extends EnemyType {

  def speed: Int = 100

  def health: Int = 100

  def damage: Int = 5

  def image: Image = Utils.getImageFromResource(DefaultConfig.BASE_ENEMY_IMAGE)

  def apply: EnemyType = Easy
}
