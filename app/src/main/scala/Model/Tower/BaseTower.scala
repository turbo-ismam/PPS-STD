package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes

/**
 * This class defines a basic type tower
 * This type of tower fires basic projectiles.
 */
private class BaseTower extends ShooterTower(ProjectileTypes.BASE_PROJECTILE) {
  override val name: String = DefaultConfig.BASE_TOWER_NAME
  override val desc: String = DefaultConfig.BASE_TOWER_NAME
}

object BaseTower {
  def apply(): TowerType = {
    val baseTower: BaseTower = new BaseTower()
    baseTower
  }
}