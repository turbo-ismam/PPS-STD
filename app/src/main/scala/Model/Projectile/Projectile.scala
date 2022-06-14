package Model.Projectile

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Tower.TowerType
import Utility.{Utils, WayPoint}
import scalafx.scene.image.Image

class Projectile(_target_pos: WayPoint,
                 origin: WayPoint,
                 firing_tower: TowerType,
                 enemy: Enemy,
                 towerController: Tower
                ) extends ProjectileType with LogHelper {


  //Diameter of the bullet.
  val projectileDiameter: Int = 10
  val cellSize = 64

  var alive: Boolean = true

  //Velocities
  var xVelocity = 0.0
  var yVelocity = 0.0

  override val speed: Double = 1300
  var damage: Double = firing_tower.damage
  var pos = origin
  val target: WayPoint = _target_pos

  def calculateDirection(): Unit = {
    val totalAllowedMovement = 1.0
    val xDistanceFromTarget = Math.abs(target.x - pos.x + 32)
    val yDistanceFromTarget = Math.abs(target.y - pos.y + 32)
    val totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget
    val xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget
    xVelocity = xPercentOfMovement
    yVelocity = totalAllowedMovement - xPercentOfMovement

    if (target.x < pos.x) xVelocity = xVelocity * -1
    if (target.y < pos.y) yVelocity = yVelocity * -1
  }

  calculateDirection()

  def isColliding(x: Double, y: Double): Boolean = {
    (x + projectileDiameter > target.x) && (x < target.x + cellSize) &&
      (y + projectileDiameter > target.y) && (y < target.y + cellSize)
  }

  override def update(delta: Double): Unit = {
    if (alive) {
      pos.y += yVelocity * speed * delta
      pos.x += xVelocity * speed * delta
      if (isColliding(pos.x, pos.y)) {
        alive = false
        enemy.takeDamage(damage.toInt)
        towerController.addProjectileToRemove(this)
      }
    }
  }

  def graphic(): Image = {
    val graphic = Utils.getImageFromResource(firing_tower.projectile_graphic)
    graphic.smooth
    graphic
  }
}
