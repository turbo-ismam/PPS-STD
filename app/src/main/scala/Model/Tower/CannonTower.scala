package Model.Tower

import Configuration.DefaultConfig
import Model.Projectile.ProjectileTypes
import Model.Tower.TowerTypes.CANNON_TOWER

/**
 * This class defines a cannon-type tower.
 * This type of tower fires large bullets.
 */
private class CannonTower extends ShooterTower(ProjectileTypes.CANNON_PROJECTILE) {

  override val name: String = DefaultConfig.CANNON_TOWER_NAME
  override val desc: String = DefaultConfig.CANNON_TOWER_DESC

  override val towerGraphic: String = DefaultConfig.CANNON_TOWER_IMAGE
  override val firingSpeed: Int = DefaultConfig.CANNON_TOWER_FIRING_SPEED
  override val price: Int = DefaultConfig.CANNON_TOWER_PRICE
  override val damage: Int = DefaultConfig.CANNON_TOWER_DAMAGE
  override val rangeInTiles: Int = DefaultConfig.CANNON_TOWER_RANGE

  override def towerType: TowerTypes.TowerType = CANNON_TOWER
}

object CannonTower {
  def apply(): TowerType = {
    val cannonTower: CannonTower = new CannonTower()
    cannonTower
  }
}