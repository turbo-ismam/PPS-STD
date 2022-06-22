package Model.Tower

import Configuration.DefaultConfig
import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy

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
   * @param x Position of the tower taking into account the range
   * @param y Position of the tower taking into account the range
   * @param enemy
   * @return true if the enemy is in a collision, false otherwise
   */
  override def isColliding(x: Double, y: Double, enemy: Enemy): Boolean = {
    val enemyPosX = enemy.enemyCurrentPosition().x
    val enemyPosY = enemy.enemyCurrentPosition().y
    (x + rangeInTiles * cellSize > enemyPosX) && (x < enemyPosX + cellSize) &&
      (y + rangeInTiles * cellSize > enemyPosY) && (y < enemyPosY + cellSize)
  }

  /**
   * Get the list of enemies via the gameController.
   * If the enemy collides with the shoot, the enemy takes damage
   * Use  {@link isColliding( x : Double, y:Double, enemy: Enemy)}
   */
  override def attack(): Unit = {
    tower.get.timeSinceLastShot = 0
    tower.get.displayShotInRange = true
    val circleRadiusX = tower.get.posX - ((rangeInTiles - 1) * 32)
    val circleRadiusY = tower.get.posY - ((rangeInTiles - 1) * 32)
    gameController.get.enemies.foreach(e => {
      if (isColliding(circleRadiusX, circleRadiusY, e)) {
        e.takeDamage(damage)
      }
    })
  }

  override def setup(tower: Tower, gameController: GameController): Unit = {
    this.tower = Option(tower)
    this.gameController = Option(gameController)
  }
}
