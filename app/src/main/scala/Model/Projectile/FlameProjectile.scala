package Model.Projectile

import Controller.GameController
import Model.Enemy.Enemy
import Model.Tower.TowerType
import Utility.WayPoint

class FlameProjectile(
                       _target_pos: WayPoint,
                       origin: WayPoint,
                       firing_tower: TowerType,
                       enemy: Enemy,
                       gameController: GameController)
  extends Projectile(
    _target_pos,
    origin,
    firing_tower,
    enemy,
    gameController
  ) {

  override val name: String = "Flame projectile"
  override val desc: String = "Flame projectile"

}
