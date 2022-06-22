package Model.Projectile

import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Projectile.Exceptions.ProjectileTypeNotExistException
import Model.Projectile.ProjectileTypes.{BASE_PROJECTILE, CANNON_PROJECTILE}
import Model.Tower.TowerType
import Utility.WayPoint

/**
 * A factory that creates Projectiles
 */
object ProjectileFactory {

  def apply(
             projectile_type: ProjectileTypes.ProjectileType,
             _target_pos: WayPoint,
             origin: WayPoint,
             firing_tower: TowerType,
             enemy: Enemy,
             towerController: Tower
           ): Projectile = {

    projectile_type match {
      case BASE_PROJECTILE =>
        Projectile(_target_pos,
          origin, firing_tower, enemy, towerController)
      case CANNON_PROJECTILE =>
        CannonProjectile(_target_pos,
          origin, firing_tower, enemy, towerController)
      case _ => throw new ProjectileTypeNotExistException("Projectile type not exist")
    }
  }
}
