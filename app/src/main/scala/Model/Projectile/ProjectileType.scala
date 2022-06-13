package Model.Projectile

import Model.Enemy.Enemy

trait ProjectileType {
  val name = "Base projectile"
  val desc = "A Base projectile type"
  val hitradius: Double = 0.7
  val speed: Double = 1300

  def move(delta: Double): Unit = {}

  def on_hit(enemy: Option[Enemy]): Unit = {}

  def update(delta: Double): Unit = {}
}
