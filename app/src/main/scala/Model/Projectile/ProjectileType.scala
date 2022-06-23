package Model.Projectile

import Configuration.DefaultConfig
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Projectile.Exceptions.ProjectileTypeNotExistException
import Model.Projectile.ProjectileTypes.{BASE_PROJECTILE, CANNON_PROJECTILE}
import Model.Tower.TowerType
import Utility.WayPoint


/**
 * A factory that creates Projectiles
 */
object ProjectileType {

  def apply(
             projectile_type: ProjectileTypes.ProjectileType,
             targetPos: WayPoint,
             origin: WayPoint,
             firingTower: TowerType,
             enemy: Enemy,
             towerController: Tower
           ): Projectile = {

    projectile_type match {
      case BASE_PROJECTILE =>
        Projectile(targetPos,
          origin, firingTower, enemy, towerController)
      case CANNON_PROJECTILE =>
        CannonProjectile(targetPos,
          origin, firingTower, enemy, towerController)
      case _ => throw new ProjectileTypeNotExistException("Projectile type not exist")
    }
  }
}


/**
 * A trait that defines the type of projectile. Every projectile inherits this trait.
 */
trait ProjectileType {
  val name = DefaultConfig.BASE_PROJECTILE_NAME
  val desc = DefaultConfig.BASE_PROJECTILE_DESC
  val speed: Double = DefaultConfig.BASE_PROJECTILE_SPEED
  val projectileDiameter: Int = DefaultConfig.BASE_PROJECTILE_DIAMETER
  val totalAllowedMovement = DefaultConfig.PROJECTILE_ALLOWED_MOVEMENT

  def update(delta: Double): Unit
}
