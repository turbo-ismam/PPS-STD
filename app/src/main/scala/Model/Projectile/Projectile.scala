package Model.Projectile

import Configuration.DefaultConfig
import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Tower.TowerType
import Utility.WayPoint

/**
 * This class defines the logic of a projectile
 *
 * @param targetPos    The position of the target to be fired
 * @param origin       The position of bullet origin. Corresponds to the position of the tower.
 * @param firing_tower The tower type that fired the bullet
 * @param enemy        the enemy to shoot
 * @param tower        The tower controller that fired
 */
class Projectile(targetPos: WayPoint, origin: WayPoint, firingTower: TowerType, enemy: Enemy, tower: Tower) extends ProjectileType with LogHelper {

  val cellSize = DefaultConfig.CELL_SIZE
  var damage: Double = firingTower.damage
  val target: WayPoint = targetPos
  var pos = origin
  var xVelocity = 0.0
  var yVelocity = 0.0
  var alive: Boolean = true

  /**
   * Calculate the direction of the bullet
   */
  def calculateDirection(): Unit = {
    val distanceFromTarget = target.distanceTo(pos)
    val totalDistanceFromTarget = distanceFromTarget.totalDistance
    val xPercentOfMovement = distanceFromTarget.x / totalDistanceFromTarget
    xVelocity = xPercentOfMovement
    yVelocity = totalAllowedMovement - xPercentOfMovement

    if (target.x < pos.x) xVelocity = xVelocity * -1
    if (target.y < pos.y) yVelocity = yVelocity * -1
  }

  calculateDirection()

  /**
   * Given an x y position, it checks whether the bullet collides with the target
   *
   * @param x
   * @param y
   * @return true if is colliding, false otherwise
   */
  def isColliding(pos: WayPoint): Boolean = {
    pos.compare(projectileDiameter, cellSize, target)
  }

  /**
   * Dynamically update the position of the projectile.
   * If it collides with an enemy, the enemy takes damage.
   *
   * @param delta bullet position update time
   */
  override def update(delta: Double): Unit = {
    if (alive) {
      pos.y += yVelocity * speed * delta
      pos.x += xVelocity * speed * delta
      if (isColliding(pos)) {
        alive = false
        enemy.takeDamage(damage.toInt)
        if (!enemy.isAlive) tower.player.incrementKillCounter()
        tower.removeProjectile(this)
      }
    }
  }
}

object Projectile {
  def apply(targetPos: WayPoint, origin: WayPoint, firingTower: TowerType, enemy: Enemy, tower: Tower): Projectile = {
    val projectile: Projectile = new Projectile(targetPos, origin, firingTower, enemy, tower)
    projectile
  }
}