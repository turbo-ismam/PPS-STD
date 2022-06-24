package Model.Tower

import Controller.GameController
import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Projectile.{ProjectileType, ProjectileTypes}
import Utility.WayPoint

/**
 * That class defines the methods of all shooting towers
 * These types of towers detect a target and shoot at it.
 *
 * @param projectileType : Type of projectile sent by tower
 */
class ShooterTower(projectileType: ProjectileTypes.ProjectileType) extends TowerType with LogHelper {

  private var tower: Option[Tower] = None
  private var gameController: Option[GameController] = None

  /**
   * Calculate the distance between the enemy and the tower that is firing
   *
   * @param enemy
   * @return a double corresponds to the calculated distance
   */
  override def findDistance(enemy: Enemy): Double = {
    val enemyPos = WayPoint(enemy.getX(), enemy.getY())
    enemyPos.diff_abs_hypot(tower.get.towerPosition)
  }

  /**
   * Check if the enemy is within range of the tower
   *
   * @param enemy
   * @return true if the enemy is in range, false otherwise
   */
  override def inRange(enemy: Enemy): Boolean = {
    val enemyPos = WayPoint(enemy.getX(), enemy.getY())
    val abs = enemyPos.diff_abs(tower.get.towerPosition)
    (abs.x < tower.get.rangeInTiles) && (abs.y < tower.get.rangeInTiles)
  }

  /**
   * Prepare a projectile to shoot the enemy.
   * The type of project will depend on the tower that made the call to this function
   *
   * @param enemy the enemy to shoot
   */
  override def fireAt(enemy: Enemy): Unit = {
    val enemyPos = WayPoint(enemy.getX(), enemy.getY())
    val towerPos = tower.get.towerPosition.clone()
    val throwProjectile = ProjectileType.apply(
      projectileType,
      enemyPos,
      towerPos,
      this,
      enemy,
      tower.get
    )
    tower.get += throwProjectile
  }

  /**
   * Through the gameController, it takes the list of enemies currently present on the map.
   * If the enemy is in range and the distance is less than the minimum distance, and the enemy is alive,
   * then it will take this enemy as the target to shoot.
   *
   * @return the chosen target.
   */
  override def chooseTarget(): Option[Enemy] = {
    var minDistance: Double = rangeInTiles
    gameController.get.enemies.foreach(enemy => {
      if (inRange(enemy) && findDistance(enemy) < minDistance && enemy.isAlive()) {
        minDistance = findDistance(enemy)
        current_target = Option(enemy)
      }
    })
    if (!current_target.isEmpty) targeted = true else targeted = false
    current_target
  }

  /**
   * If a target has been chosen, then call {@link fireAt( enemy : Enemy)} to fire
   */
  override def attack(): Unit = {
    tower.get.timeSinceLastShot = 0
    current_target match {
      case None => logger.debug("No target select for tower in position {}-{} ", tower.get.towerPosition.x, tower.get.towerPosition.y)
      case Some(current_target) =>
        if (current_target.isAlive()) {
          fireAt(current_target)
        }
    }
  }

  override def setup(tower: Tower, gameController: GameController): Unit = {
    this.tower = Option(tower)
    this.gameController = Option(gameController)
  }
}
