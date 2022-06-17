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
        BaseTower()
      case CANNON_TOWER =>
        CannonTower()
      case FLAME_TOWER =>
        FlameTower()
      case _ => throw new TowerNotExistException("Projectile type not exist")
    }

  }
}

/**
 * A trait that defines the type of tower. Every tower inherits this trait.
 */
trait TowerType {
  val name: String = DefaultConfig.BASE_TOWER_NAME
  val desc: String = DefaultConfig.BASE_TOWER_DESC
  val tower_graphic: String = DefaultConfig.BASE_TOWER_IMAGE
  val projectile_graphic: String = DefaultConfig.BASE_PROJECTILE_IMAGE
  val damage: Int = DefaultConfig.BASE_TOWER_DAMAGE
  val circularRadiusTowerShootColor: Color = Color.rgb(255, 0, 0, 0.5)
  val rangeInTiles: Int = DefaultConfig.BASE_TOWER_RANGE
  val firingSpeed: Int = DefaultConfig.BASE_TOWER_FIRING_SPEED
  val price: Int = DefaultConfig.BASE_TOWER_PRICE

  var targeted: Boolean = false
  var current_target: Option[Enemy] = None
  var amount: Int = 0

  def findDistance(e: Enemy): Double = 0.0

  def isColliding(x: Double, y: Double, e: Enemy): Boolean = false

  def in_range(e: Enemy): Boolean = false

  def fire_at(enemy: Enemy): Unit = {}

  def choose_target(): Option[Enemy] = None

  def attack(): Unit

  def setup(tower: Tower, gameController: GameController): Unit

  def tower_type: TowerTypes.TowerType = BASE_TOWER
}