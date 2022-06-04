package Model.Tower

import Configuration.DefaultConfig
import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Tower.Exceptions.TowerNotExistException
import Model.Tower.TowerTypes._

import scala.collection.mutable.Buffer

object TowerType {

  def apply(kind: TowerTypes.TowerType): TowerType = kind match {
    case BASE_TOWER =>
      BaseTower
    case CANNON_TOWER =>
      CannonTower
    case FLAME_TOWER =>
      FlameTower
    case _ =>
      throw new TowerNotExistException("Tower type not found")
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

  //Number of towerType created
  var amount = 0

  def attack_from(tower: Tower, gameController: GameController): () => Boolean = { () => true }
}