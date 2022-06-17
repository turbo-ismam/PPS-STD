package Model.Projectile

import Configuration.DefaultConfig
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

  val cellSize = DefaultConfig.CELL_SIZE
  var damage: Double = firing_tower.damage
  val target: WayPoint = _target_pos


  var pos = origin
  var xVelocity = 0.0
  var yVelocity = 0.0
  var alive: Boolean = true

  def calculateDirection(): Unit = {
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

object Projectile {
  def apply(_target_pos: WayPoint,
            origin: WayPoint,
            firing_tower: TowerType,
            enemy: Enemy,
            towerController: Tower): Projectile = {
    val projectile: Projectile =
      new Projectile(
        _target_pos,
        origin,
        firing_tower,
        enemy,
        towerController
      )
    projectile
  }
}