package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes

class BaseTower extends ShooterTower(ProjectileTypes.BASE_PROJECTILE) {
  override val name = DefaultConfig.BASE_TOWER_NAME
  override val desc = DefaultConfig.BASE_TOWER_NAME
}
