package Model.Tower

import Controller.GameController
import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Projectile.{ProjectileFactory, ProjectileTypes}
import Utility.WayPoint

import java.lang.System.Logger.Level

/**
 * That class defines the methods of all shooting towers
 * These types of towers detect a target and shoot at it.
 *
 * @param projectile_type : Type of projectile sent by tower
 */
class ShooterTower(projectile_type: ProjectileTypes.ProjectileType) extends TowerType with LogHelper {

  private var towerController: Option[Tower] = None
  private var gameController: Option[GameController] = None

  override def findDistance(e: Enemy): Double = {
    val enemyPosX = e.enemyCurrentPosition().x
    val enemyPosY = e.enemyCurrentPosition().y
    val xDistance = math.abs(enemyPosX - towerController.get.posX)
    val yDistance = math.abs(enemyPosY - towerController.get.posY)
    math.hypot(xDistance, yDistance)
  }

  override def in_range(e: Enemy): Boolean = {
    val enemyPosX = e.enemyCurrentPosition().x
    val enemyPosY = e.enemyCurrentPosition().y
    val xDistance = math.abs(enemyPosX - towerController.get.posX)
    val yDistance = math.abs(enemyPosY - towerController.get.posY)
    (yDistance < towerController.get.rangeInTiles) && (xDistance < towerController.get.rangeInTiles)
  }

  override def fire_at(enemy: Enemy): Unit = {
    val enemyPosX = enemy.enemyCurrentPosition().x
    val enemyPosY = enemy.enemyCurrentPosition().y
    val enemyPos = new WayPoint(enemyPosX, enemyPosY)
    val tower_pos = new WayPoint(towerController.get.posX, towerController.get.posY)
    val throw_projectile = ProjectileFactory(
      projectile_type,
      enemyPos,
      tower_pos,
      this,
      enemy,
      towerController.get
    )
    throw_projectile.damage = damage
    towerController.get += throw_projectile
  }

  override def choose_target(): Option[Enemy] = {
    var minDistance: Double = rangeInTiles
    gameController.get.enemies.foreach(enemy => {
      if (in_range(enemy) && findDistance(enemy) < minDistance && enemy.isAlive()) {
        minDistance = findDistance(enemy)
        current_target = Option(enemy)
      }
    })
    if (!current_target.isEmpty) targeted = true else targeted = false
    current_target
  }

  override def attack(): Unit = {
    towerController.get.timeSinceLastShot = 0
    current_target match {
      case None => logger.debug("No target select for tower in position {}-{} ", towerController.get.posX, towerController.get.posY)
      case Some(current_target) =>
        if (current_target.isAlive()) {
          fire_at(current_target)
        }
    }
  }

  override def setup(tower: Tower, gameController: GameController): Unit = {
    this.towerController = Option(tower)
    this.gameController = Option(gameController)
  }
}
