package Model.Tower

import Configuration.DefaultConfig

object CannonTower extends ShooterTower(2) {

  override val name = DefaultConfig.CANNON_TOWER_NAME
  override val desc = DefaultConfig.CANNON_TOWER_DESC

  override val tower_graphic = DefaultConfig.CANNON_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.CANNON_PROJECTILE_IMAGE
  override val charging_time = DefaultConfig.CANNON_TOWER_CHARGING_TIME
}
