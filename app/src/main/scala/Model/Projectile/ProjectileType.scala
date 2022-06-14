package Model.Projectile

trait ProjectileType {
  val name = "Base projectile"
  val desc = "A Base projectile type"
  val hitradius: Double = 0.7
  val speed: Double = 1300

  def update(delta: Double): Unit
}
