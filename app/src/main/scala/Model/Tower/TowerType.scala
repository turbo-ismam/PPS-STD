package Model.Tower

import Configuration.DefaultConfig
import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Tower.Exceptions.TowerNotExistException
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import scalafx.scene.paint.Color

object TowerType {

  def apply(
             tower_type: TowerTypes.TowerType
           ): TowerType = {

    tower_type match {
      case BASE_TOWER =>
        new BaseTower()
      case CANNON_TOWER =>
        new CannonTower()
      case FLAME_TOWER =>
        new FlameTower()
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
  var damage = DefaultConfig.TOWER_DAMAGE
  var rangeInTiles = DefaultConfig.TOWER_RANGE
  var firingSpeed = DefaultConfig.TOWER_FIRING_SPEED
  var price = DefaultConfig.TOWER_PRICE
  var targeted = false
  var current_target: Option[Enemy] = None
  //Number of towerType created
  var amount = 0

  //Circular Radius Tower
  val circularRadiusTowerShootColor = Color.rgb(255, 0, 0, 0.5)

  def findDistance(e: Enemy): Double = 0.0

  def in_range(e: Enemy): Boolean = false

  def fire_at(enemy: Enemy): Unit = {}

  def choose_target(): Option[Enemy] = None

  def attack(): Unit

  def apply(tower: Tower, gameController: GameController): Unit

  def tower_type:TowerTypes.TowerType = BASE_TOWER
}