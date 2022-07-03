package Model.Projectile

import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Projectile.Exceptions.ProjectileTypeNotExistException
import Model.Projectile.ProjectileTypes.{BASE_PROJECTILE, CANNON_PROJECTILE}
import Model.Tower.TowerType
import Utility.Configuration.DefaultConfig
import Utility.WayPoint
import scalafx.scene.paint.Color


/**
 * A factory that creates Projectiles
 */
object ProjectileType {

  def apply(projectileType: ProjectileTypes.ProjectileType, targetPos: WayPoint,
            origin: WayPoint, firingTower: TowerType, enemy: Enemy, tower: Tower): Projectile = {

    projectileType match {
      case BASE_PROJECTILE =>
        Projectile(targetPos, origin, firingTower, enemy, tower)
      case CANNON_PROJECTILE =>
        CannonProjectile(targetPos, origin, firingTower, enemy, tower)
      case _ => throw new ProjectileTypeNotExistException("Projectile type not exist")
    }
  }
}


/**
 * A trait that defines the type of projectile. Every projectile inherits this trait.
 */
trait ProjectileType {
  val name: String = DefaultConfig.BASE_PROJECTILE_NAME
  val desc: String = DefaultConfig.BASE_PROJECTILE_DESC
  val speed: Double = DefaultConfig.BASE_PROJECTILE_SPEED
  val projectileDiameter: Int = DefaultConfig.BASE_PROJECTILE_DIAMETER
  val totalAllowedMovement: Double = DefaultConfig.PROJECTILE_ALLOWED_MOVEMENT
  val projectileColor: Color = Color.Black

  def isColliding(pos: WayPoint): Boolean

  def update(delta: Double): Unit
}
