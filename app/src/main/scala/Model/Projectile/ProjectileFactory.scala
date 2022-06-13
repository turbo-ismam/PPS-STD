package Model.Projectile

import Controller.GameController
import Model.Enemy.Enemy
import Model.Projectile.Exceptions.ProjectileTypeNotExistException
import Model.Projectile.ProjectileTypes.{BASE_PROJECTILE, CANNON_PROJECTILE, FLAME_PROJECTILE}
import Model.Tower.TowerType
import Utility.WayPoint

object ProjectileFactory {

  def apply(
             projectile_type: ProjectileTypes.ProjectileType,
             _target_pos: WayPoint,
             origin: WayPoint,
             firing_tower: TowerType,
             enemy: Enemy,
             gameController: GameController
           ): Projectile = {

    projectile_type match {
      case BASE_PROJECTILE =>
        new Projectile(_target_pos,
          origin, firing_tower, enemy, gameController)
      case CANNON_PROJECTILE =>
        new CannonProjectile(_target_pos,
          origin, firing_tower, enemy, gameController)
      case FLAME_PROJECTILE =>
        new FlameProjectile(_target_pos,
          origin, firing_tower, enemy, gameController)
      case _ => throw new ProjectileTypeNotExistException("Projectile type not exist")
    }
  }
}
