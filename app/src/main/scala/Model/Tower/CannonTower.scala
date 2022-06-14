package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes
import Model.Tower.TowerTypes.CANNON_TOWER

class CannonTower extends ShooterTower(ProjectileTypes.CANNON_PROJECTILE) {

  override val name = DefaultConfig.CANNON_TOWER_NAME
  override val desc = DefaultConfig.CANNON_TOWER_DESC

  override val tower_graphic = DefaultConfig.CANNON_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.CANNON_PROJECTILE_IMAGE

  override def tower_type: TowerTypes.TowerType = CANNON_TOWER
}
