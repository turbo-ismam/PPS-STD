package Model.Projectile

import Controller.GameController
import Model.Enemy.Enemy
import Model.Tower.TowerType
import Utility.WayPoint

class CannonProjectile(_target_pos: WayPoint,
                       origin: WayPoint,
                       firing_tower: TowerType,
                       gameController: GameController
                      ) extends Projectile(
  _target_pos,
  origin,
  firing_tower,
  gameController) {

  override val hitradius: Double = 10
  override val name: String = "Cannon projectile"
  override val desc: String = "Cannon projectile"

  override def on_hit(enemy: Option[Enemy]): Unit = {
    val targets = gameController.enemies
      .filter(enemy => {
        val enemyPosX = enemy.enemyCurrentPosition().x
        val enemyPosY = enemy.enemyCurrentPosition().y
        val enemyPost = new WayPoint(enemyPosX, enemyPosY)
        pos.distance_to(enemyPost) < hitradius * 64
      })
    targets.foreach(target => {
      target.takeDamage(damage.toInt)
    })
    gameController.addProjectileToRemove(this)
  }
}
