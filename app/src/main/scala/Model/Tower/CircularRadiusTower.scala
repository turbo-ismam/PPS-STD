package Model.Tower

import Configuration.DefaultConfig
import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Utility.WayPoint

/**
 * This class defines methods of towers that fire in a circular beam.
 * They don't choose a specific enemy to hit,
 * but any enemy that passes within its range and gets hit,
 * takes damage
 */
class CircularRadiusTower extends TowerType {

  private var tower: Option[Tower] = None
  private var gameController: Option[GameController] = None
  val cellSize = DefaultConfig.CELL_SIZE

  /**
   * Check given an enemy, if it collides with the shoot
   *
   * @param radius  Position of the tower taking into account the range
   * @param enemy
   * @return true if the enemy is in a collision, false otherwise
   */
  override def isColliding(radius: WayPoint, enemy: Enemy): Boolean = {
    val enemyPos = WayPoint(enemy.getX(), enemy.getY())
    radius.compareInRange(rangeInTiles, cellSize, enemyPos)
  }

  /**
   * Get the list of enemies via the gameController.
   * If the enemy collides with the shoot, the enemy takes damage
   * Use  {@link isColliding( x : Double, y:Double, enemy: Enemy)}
   */
  override def attack(): Unit = {
    tower.get.timeSinceLastShot = 0
    tower.get.displayShotInRange = true
    val circleRadius: WayPoint = tower.get.circularRadius
    gameController.get.enemies.foreach(e => {
      if (isColliding(circleRadius, e)) {
        e.takeDamage(damage)
      }
    })
  }

  override def setup(tower: Tower, gameController: GameController): Unit = {
    this.tower = Option(tower)
    this.gameController = Option(gameController)
  }
}
