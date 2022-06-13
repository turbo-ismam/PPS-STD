package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes

class CannonTower(projectile_type: ProjectileTypes.ProjectileType) extends ShooterTower(projectile_type) {

  override val name = DefaultConfig.CANNON_TOWER_NAME
  override val desc = DefaultConfig.CANNON_TOWER_DESC

  override val tower_graphic = DefaultConfig.CANNON_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.CANNON_PROJECTILE_IMAGE

}
