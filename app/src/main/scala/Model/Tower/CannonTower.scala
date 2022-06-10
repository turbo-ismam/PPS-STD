package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileFactory

object CannonTower extends ShooterTower(ProjectileFactory.CANNON_PROJECTILE) {

  override val name = DefaultConfig.CANNON_TOWER_NAME
  override val desc = DefaultConfig.CANNON_TOWER_DESC

  override val tower_graphic = DefaultConfig.CANNON_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.CANNON_PROJECTILE_IMAGE
  override val charging_time = DefaultConfig.CANNON_TOWER_CHARGING_TIME
  override val spread: Double = 2.0
}
