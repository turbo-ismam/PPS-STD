package Model.Tower

import Configuration.DefaultConfig
import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Projectile.ProjectileTypes.{BASE_PROJECTILE, CANNON_PROJECTILE, FLAME_PROJECTILE}
import Model.Tower.Exceptions.TowerNotExistException
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}

import scala.collection.mutable.Buffer

object TowerType {

  def apply(
             tower_type: TowerTypes.TowerType
           ): TowerType = {

    tower_type match {
      case BASE_TOWER =>
        new ShooterTower(
          BASE_PROJECTILE
        )
      case CANNON_TOWER =>
        new CannonTower(
          CANNON_PROJECTILE
        )
      case FLAME_TOWER =>
        new FlameTower(
          FLAME_PROJECTILE
        )
      case _ => throw new TowerNotExistException("Projectile type not exist")
    }

  }
}

/**
 * A trait that defines the type of tower. Every tower inherits this trait.
 */
trait TowerType {
  val name = DefaultConfig.BASE_TOWER_NAME
  val desc = DefaultConfig.BASE_TOWER_DESC
  val tower_graphic = DefaultConfig.BASE_TOWER_IMAGE
  val projectile_graphic = DefaultConfig.BASE_PROJECTILE_IMAGE
  var enemies = Buffer[Enemy]()
  var damage = DefaultConfig.TOWER_DAMAGE
  var rangeInTiles = DefaultConfig.TOWER_RANGE
  var firingSpeed = DefaultConfig.TOWER_FIRING_SPEED
  var price = DefaultConfig.TOWER_PRICE
  val sell_cost = DefaultConfig.TOWER_SELL_COST
  val charging_time = DefaultConfig.TOWER_CHARGING_TIME
  val spread = 0.0
  var targeted = false
  var current_target: Option[Enemy] = None
  var timeSinceLastShot: Double = 0
  //Number of towerType created
  var amount = 0

  def findDistance(e: Enemy): Double

  def in_range(e: Enemy): Boolean

  def fire_at(enemy: Enemy): Unit

  def choose_target(): Option[Enemy]

  def attack(): Unit

  def apply(tower: Tower, gameController: GameController): Unit
}