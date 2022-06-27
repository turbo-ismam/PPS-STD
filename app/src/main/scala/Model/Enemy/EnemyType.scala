package Model.Enemy
import scalafx.scene.image.Image

/**
 * A trait that defines the type of enemy. Every enemy type inherits this trait.
 */
trait EnemyType {
  def speed: Int

  def health: Int

  def width: Int = 64

  def height: Int = 64

  def damage: Int

  def image: Image
}




