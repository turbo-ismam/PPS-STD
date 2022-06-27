package Controller.Tower

import Controller.GameController
import Model.Player
import Model.Projectile.Projectile
import Model.Tower.{CircularRadiusTower, TowerType}
import Utility.{Utils, WayPoint}
import scalafx.scene.image.Image

import scala.collection.mutable.ListBuffer

/**
 * Tower superclass from which evey special tower is derived
 *
 * @param tower_type     : Type of tower
 * @param owner          player that build this tower
 * @param x              : position of tower in the map
 * @param y              : position of tower in the map
 * @param gameController controller to get all info about game status
 */
class Tower(tower_type: TowerType,
            owner: Player,
            position: WayPoint,
            gameController: GameController) {

  private val _player: Player = owner
  private val _towerPosition: WayPoint = position
  private val _towerType = tower_type
  private val _damage = tower_type.damage
  private val _rangeInTiles = tower_type.rangeInTiles
  private val _firingSpeed = tower_type.firingSpeed
  private val _projectiles = new ListBuffer[Projectile]
  private val _junkProjectiles = new ListBuffer[Projectile]
  private var _circularRadius: WayPoint = WayPoint(0, 0)
  private var _timeSinceLastShot: Double = 0
  private var _displayShotInRange: Boolean = false


  if (towerType.isInstanceOf[CircularRadiusTower]) {
    circularRadius = towerPosition.circularRadius(rangeInTiles)
  }

  towerType.setup(this, gameController)

  def graphic(): Image = {
    val graphic = Utils.getImageFromResource(towerType.towerGraphic)
    graphic.smooth
    graphic
  }

  def clone(newPosition: WayPoint): Tower = {
    Tower(TowerType(towerType.towerType), player, newPosition, gameController)
  }

  def +=(projectile: Projectile): Unit = {
    projectiles += projectile
  }

  def -=(projectile: Projectile): Unit = {
    projectiles -= projectile
  }

  def removeProjectile(projectile: Projectile): Unit = {
    junkProjectiles += projectile
  }

  def imagePath: String = towerType.towerGraphic

  def towerType: TowerType = _towerType

  def player: Player = _player

  def towerPosition: WayPoint = _towerPosition

  def damage: Int = _damage

  def rangeInTiles: Int = _rangeInTiles

  def firingSpeed: Int = _firingSpeed

  def projectiles: ListBuffer[Projectile] = _projectiles

  def junkProjectiles: ListBuffer[Projectile] = _junkProjectiles

  def circularRadius: WayPoint = _circularRadius

  def timeSinceLastShot: Double = _timeSinceLastShot

  def displayShotInRange: Boolean = _displayShotInRange

  def price: Int = towerType.price

  def name: String = towerType.name

  def desc: String = towerType.desc

  def timeSinceLastShot_=(timeSinceLastShot: Double): Unit = {
    _timeSinceLastShot = timeSinceLastShot
  }

  def displayShotInRange_=(displayShotInRange: Boolean): Unit = {
    _displayShotInRange = displayShotInRange
  }

  private def circularRadius_=(value: WayPoint): Unit = {
    _circularRadius = value
  }
}

object Tower {
  def apply(towerType: TowerType, owner: Player, position: WayPoint, gameController: GameController): Tower = {
    val tower: Tower = new Tower(towerType, owner, position, gameController)
    tower
  }
}