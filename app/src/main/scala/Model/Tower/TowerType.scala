package Model.Tower

import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Tower.Exceptions.TowerNotExistException
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Utility.Configuration.DefaultConfig
import Utility.WayPoint
import scalafx.scene.paint.Color

/**
 * A factory that creates Towers
 */
object TowerType {

  def apply(tower_type: TowerTypes.TowerType): TowerType = {

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
  val towerGraphic: String = DefaultConfig.BASE_TOWER_IMAGE
  val damage: Int = DefaultConfig.BASE_TOWER_DAMAGE
  val circularRadiusTowerShootColor: Color = Color.rgb(255, 0, 0, 0.5)
  val rangeInTiles: Int = DefaultConfig.BASE_TOWER_RANGE
  val firingSpeed: Int = DefaultConfig.BASE_TOWER_FIRING_SPEED
  val price: Int = DefaultConfig.BASE_TOWER_PRICE
  var targeted: Boolean = false
  var current_target: Option[Enemy] = None

  def findDistance(enemy: Enemy): Double = 0.0

  def isColliding(radius: WayPoint, enemy: Enemy): Boolean = false

  def inRange(enemy: Enemy): Boolean = false

  def fireAt(enemy: Enemy): Unit = {}

  def chooseTarget(): Option[Enemy] = None

  def attack(): Unit

  def setup(tower: Tower, gameController: GameController): Unit

  def towerType: TowerTypes.TowerType = BASE_TOWER
}