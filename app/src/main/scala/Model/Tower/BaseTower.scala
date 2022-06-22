package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes

/**
 * This class defines a basic type tower
 * This type of tower fires basic projectiles.
 */
class BaseTower extends ShooterTower(ProjectileTypes.BASE_PROJECTILE) {
  override val name = DefaultConfig.BASE_TOWER_NAME
  override val desc = DefaultConfig.BASE_TOWER_NAME
}

object BaseTower {
  def apply(): BaseTower = {
    val baseTower: BaseTower = new BaseTower()
    baseTower
  }
}