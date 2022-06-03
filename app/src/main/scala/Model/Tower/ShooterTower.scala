package Model.Tower

import Controller.Tower.Tower
import Model.Enemy.Enemy

/**
 * That class defines the methods of all shoting towers
 *
 * @param projectile_type : Type of projectile sent by tower
 */
class ShooterTower(projectile_type: Int) extends TowerType {

  override def attack_from(tower: Tower, gameState: Any): () => Boolean = {

    def in_range(enemy: Enemy): Boolean = ???

    def closest_to(x: Double, y: Double): Option[Enemy] = {
      def distance_comp(x: Enemy, y: Enemy): Boolean = ???

      None
    }

    def get_target(): Option[Enemy] = ??? /*This return enemy closest_to tower ; call close_to*/

    def attack(): Boolean = ??? /*This get target. Is target is null (That means no Enemy in the range) do nothing, otherwise do attack to the target*/

    attack
  }

}
