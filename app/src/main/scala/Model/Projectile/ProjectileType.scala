package Model.Projectile

import Configuration.DefaultConfig

/**
 * A trait that defines the type of projectile. Every projectile inherits this trait.
 */
trait ProjectileType {
  val name = "Base projectile"
  val desc = "A Base projectile type"
  val speed: Double = DefaultConfig.BASE_PROJECTILE_SPEED
  val projectileDiameter: Int = DefaultConfig.BASE_PROJECTILE_DIAMETER
  val totalAllowedMovement = DefaultConfig.PROJECTILE_ALLOWED_MOVEMENT

  def update(delta: Double): Unit
}
