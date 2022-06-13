package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes

class FlameTower(projectile_type: ProjectileTypes.ProjectileType) extends ShooterTower(projectile_type) {

  override val name = DefaultConfig.FLAME_TOWER_NAME
  override val desc = DefaultConfig.FLAME_TOWER_DESC

  override val tower_graphic = DefaultConfig.FLAME_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.FLAME_PROJECTILE_IMAGE
}
