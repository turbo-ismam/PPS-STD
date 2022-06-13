package Model.Projectile

import Controller.GameController
import Model.Enemy.Enemy
import Model.Tower.TowerType
import Utility.WayPoint

class CannonProjectile(_target_pos: WayPoint,
                       origin: WayPoint,
                       firing_tower: TowerType,
                       enemy: Enemy,
                       gameController: GameController)
  extends Projectile(
    _target_pos,
    origin,
    firing_tower,
    enemy,
    gameController) {

  override val name: String = "Cannon projectile"
  override val desc: String = "Cannon projectile"
  override val projectileDiameter: Int = 50
  override val speed: Double = 1300
}
