package Controller.Tower

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
 * @param tower_type : Type of tower
 * @param x          : position of tower in the map
 * @param y          : position of tower in the map
 * @param gamestate  Maybe can be an observable or something where tower can get game status
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

  if (tower_type.isInstanceOf[CircularRadiusTower]) {
    circleRadiusX = posX - ((rangeInTiles - 1) * 32)
    circleRadiusY = posY - ((rangeInTiles - 1) * 32)
  }
  //Setup tower
  tower_type.apply(this, gameController)

  def update(delta: Double): Unit = {
    if (tower_type.isInstanceOf[ShooterTower]) {
      if (!tower_type.targeted) tower_type.choose_target()
      timeSinceLastShot += delta
      if (timeSinceLastShot > firingSpeed && !gameController.enemies.isEmpty) tower_type.attack()
      tower_type.choose_target()

      projectiles.foreach(projectile => {
        projectile.update(delta)
        if (projectile.alive) {
          val x = projectile.pos.x
          val y = projectile.pos.y
          DrawingManager.drawCircle(x, y, projectile.projectileDiameter, Color.Black)
        }
      })

      //Avoid ConcurrentModificationException
      //I can't do gameController -= projectile on foreach
      // more info here: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ConcurrentModificationException.html
      projectiles --= toRemoveProjectiles

      DrawingManager.drawTower(posX, posY, graphic())
    }
    if (tower_type.isInstanceOf[CircularRadiusTower]) {
      timeSinceLastShot += delta
      if (timeSinceLastShot < 0.5) {
        displayShotInRange = true
        DrawingManager.drawTower(posX, posY, graphic())
        displayShotInRange(tower_type.circularRadiusTowerShootColor)
      } else {
        displayShotInRange = false
        DrawingManager.drawTower(posX, posY, graphic())
      }
      if (timeSinceLastShot > firingSpeed) {
        displayShotInRange = true
        tower_type.attack()
      }
    }
  }

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

  def displayShotInRange(color: Color): Unit = {
    if (tower_type.isInstanceOf[CircularRadiusTower]) {
      displayShotInRange = true
      DrawingManager.drawCircle(circleRadiusX, circleRadiusY, rangeInTiles * 64, color)
    }
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
