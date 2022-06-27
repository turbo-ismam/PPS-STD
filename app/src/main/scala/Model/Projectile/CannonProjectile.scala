package Model.Projectile

import Configuration.DefaultConfig
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Tower.TowerType
import Utility.WayPoint
import scalafx.scene.paint.Color

class CannonProjectile(targetPos: WayPoint, origin: WayPoint, firingTower: TowerType, enemy: Enemy, tower: Tower)
  extends Projectile(targetPos, origin, firingTower, enemy, tower) {

  override val name: String = DefaultConfig.CANNON_PROJECTILE_NAME
  override val desc: String = DefaultConfig.CANNON_PROJECTILE_DESC
  override val projectileDiameter: Int = DefaultConfig.CANNON_PROJECTILE_DIAMETER
  override val speed: Double = DefaultConfig.CANNON_PROJECTILE_SPEED
  override val projectileColor: Color = Color.rgb(255, 140, 0)
}

object CannonProjectile {
  def apply(targetPos: WayPoint, origin: WayPoint, firingTower: TowerType, enemy: Enemy, tower: Tower): Projectile = {
    val cannonProjectile: CannonProjectile = new CannonProjectile(targetPos, origin, firingTower, enemy, tower)
    cannonProjectile
  }
}
