package Controller.Tower

import Configuration.DefaultConfig
import Controller.{DrawingManager, GameController}
import Model.Player
import Model.Projectile.Projectile
import Model.Tower.{CircularRadiusTower, FlameTower, ShooterTower, TowerType}
import Utility.Utils
import scalafx.scene.image.Image
import scalafx.scene.paint.Color

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
            x: Double,
            y: Double,
            gameController: GameController) {

  val player: Player = owner
  val posX = x
  val posY = y
  val towerType = tower_type
  var damage = tower_type.damage
  var rangeInTiles = tower_type.rangeInTiles
  var firingSpeed = tower_type.firingSpeed
  val projectiles = new ListBuffer[Projectile]
  val toRemoveProjectiles = new ListBuffer[Projectile]
  var circleRadiusX: Double = 0
  var circleRadiusY: Double = 0
  var timeSinceLastShot: Double = 0
  var displayShotInRange: Boolean = false
  val cellSize = DefaultConfig.CELL_SIZE

  if (tower_type.isInstanceOf[CircularRadiusTower]) {
    circleRadiusX = posX - ((rangeInTiles - 1) * 32)
    circleRadiusY = posY - ((rangeInTiles - 1) * 32)
  }

  //Setup tower
  tower_type.setup(this, gameController)

  //Getters
  def name(): String = {
    tower_type.name
  }

  def desc(): String = {
    tower_type.desc
  }

  def price(): Int = {
    tower_type.price
  }

  def graphic(): Image = {
    val graphic = Utils.getImageFromResource(tower_type.tower_graphic)
    graphic.smooth
    graphic
  }

  def tower_type(): TowerType = towerType

  def image_path(): String = {
    tower_type.tower_graphic
  }
  
  def clone(x: Double, y: Double): Tower = {
    new Tower(TowerType(tower_type.tower_type), player, x, y, gameController)
  }

  def +=(projectile: Projectile): Unit = {
    projectiles += projectile
  }

  def -=(projectile: Projectile): Unit = {
    projectiles -= projectile
  }

  def addProjectileToRemove(projectile: Projectile): Unit = {
    toRemoveProjectiles += projectile
  }

}
