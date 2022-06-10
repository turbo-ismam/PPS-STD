package Model.Projectile

import Controller.GameController
import Model.Projectile.Exceptions.ProjectileTypeNotExistException
import Model.Tower.TowerType
import Utility.WayPoint

object ProjectileFactory {

  val BASE_PROJECTILE = 0
  val CANNON_PROJECTILE = 1
  val FLAME_PROJECTILE = 2

  def apply(
             projectile_type: Int,
             _target_pos: WayPoint,
             origin: WayPoint,
             firing_tower: TowerType,
             gameController: GameController
           ): Projectile = {

    projectile_type match {
      case BASE_PROJECTILE =>
        new Projectile(_target_pos,
          origin, firing_tower, gameController)
      case CANNON_PROJECTILE =>
        new CannonProjectile(_target_pos,
          origin, firing_tower, gameController)
      case FLAME_PROJECTILE =>
        new FlameProjectile(_target_pos,
          origin, firing_tower, gameController)
      case _ => throw new ProjectileTypeNotExistException("Projectile type not exist")
    }
  }
}
