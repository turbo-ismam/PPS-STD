package Model.Projectile

/**
 * An Emum that defines the types of projectile available
 */
object ProjectileTypes extends Enumeration {
  type ProjectileType = Value
  val BASE_PROJECTILE = Value(1)
  val CANNON_PROJECTILE = Value(2)
}
