package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileFactory

object FlameTower extends ShooterTower(ProjectileFactory.FLAME_PROJECTILE) {

  override val name = DefaultConfig.FLAME_TOWER_NAME
  override val desc = DefaultConfig.FLAME_TOWER_DESC

  override val tower_graphic = DefaultConfig.FLAME_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.FLAME_PROJECTILE_IMAGE
  override val charging_time = DefaultConfig.FLAME_TOWER_CHARGING_TIME
  override val spread: Double = 1.0
}
