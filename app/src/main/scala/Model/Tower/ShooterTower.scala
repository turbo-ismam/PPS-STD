package Model.Tower

import Controller.GameController
import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Projectile.ProjectileFactory
import Utility.{Utils, WayPoint}

/**
 * That class defines the methods of all shooting towers
 *
 * @param projectile_type : Type of projectile sent by tower
 */
class ShooterTower(projectile_type: Int) extends TowerType with LogHelper {

  override def attack_from(tower: Tower, gameController: GameController): () => Boolean = {

    def in_range(enemy: Enemy): Boolean = {
      val x = enemy.enemyCurrentPosition().x
      val y = enemy.enemyCurrentPosition().y
      val enemyPos = new WayPoint(x, y)
      val towerPos = new WayPoint(tower.posX, tower.posY)
      (enemyPos - towerPos).norm() <= tower.rangeInTiles * 64
    }

    def fire_at(enemy: Enemy): Unit = {
      val enemyPosX = enemy.enemyCurrentPosition().x
      val enemyPosY = enemy.enemyCurrentPosition().y
      val enemyPos = new WayPoint(enemyPosX, enemyPosY)
      val target_pos = enemyPos + (WayPoint.random() * 2 - new WayPoint(1, 1)) * spread
      val tower_pos = new WayPoint(tower.posX, tower.posY)
      val throw_projectile = ProjectileFactory(
        projectile_type,
        target_pos,
        tower_pos,
        this,
        gameController
      )
      throw_projectile.speed = firingSpeed
      throw_projectile.damage = damage
      gameController += throw_projectile
    }

    def closest_to(x: Double, y: Double): Option[Enemy] = {

      def distance_comp(e1: Enemy, e2: Enemy): Boolean = {
        val e1Pos = new WayPoint(e1.enemyCurrentPosition().x, e1.enemyCurrentPosition().y)
        val e2Pos = new WayPoint(e2.enemyCurrentPosition().x, e2.enemyCurrentPosition().y)
        val currentPoint = new WayPoint(x, y)
        (e1Pos - currentPoint).norm() < (e2Pos - currentPoint).norm()
      }

      val enemies =
        gameController.enemies
          .filter(_.isAlive())
          .filter(in_range)
          .sortWith(distance_comp)

      enemies.headOption
    }

    def get_target(): Option[Enemy] = {
      closest_to(tower.posX, tower.posY)
    }

    def attack(): Boolean = {
      val target = get_target()
      if (target == None)
        return false
      fire_at(target.get)
      true
    }

    attack
  }
}
