package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileFactory

object BaseTower extends ShooterTower(ProjectileFactory.BASE_PROJECTILE) {

  override val name = DefaultConfig.BASE_TOWER_NAME
  override val desc = DefaultConfig.BASE_TOWER_NAME

}
