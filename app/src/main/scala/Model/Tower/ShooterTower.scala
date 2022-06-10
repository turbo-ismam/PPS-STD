package Model.Tower

import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Utility.Utils

/**
 * That class defines the methods of all shooting towers
 *
 * @param projectile_type : Type of projectile sent by tower
 */
class ShooterTower(projectile_type: Int) extends TowerType {

  override def attack_from(tower: Tower, gameController: GameController): () => Boolean = {

    def in_range(enemy: Enemy): Boolean = {
      val x = enemy.enemyCurrentPosition().x
      val y = enemy.enemyCurrentPosition().y
      Utils.normalize(x - tower.posX, y - tower.posY) <= tower.rangeInTiles
    }

    def fire_at(enemy: Enemy): Unit = {
      //Create projectile and fire
    }

    def closest_to(x: Double, y: Double): Option[Enemy] = {

      def distance_comp(enemy1: Enemy, enemy2: Enemy): Boolean = {
        val enemy1X = enemy1.enemyCurrentPosition().x
        val enemy1Y = enemy1.enemyCurrentPosition().y
        val enemy2X = enemy2.enemyCurrentPosition().x
        val enemy2Y = enemy2.enemyCurrentPosition().y
        Utils.normalize(enemy1X - x, enemy1Y - y) < Utils.normalize(enemy2X - x, enemy2Y - y)
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
