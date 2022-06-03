package Controller.Tower

import Model.Tower.TowerType
import Utility.Utils
import scalafx.scene.image.Image

/**
 * Tower superclass from which evey special tower is derived
 *
 * @param tower_type : Type of tower
 * @param x          : position of tower in the map
 * @param y          : position of tower in the map
 * @param gamestate  Maybe can be an observable or something where tower can get game status
 */
class Tower(tower_type: TowerType,
            x: Double,
            y: Double,
            gamestate: Any) {

  val posX = x
  val posY = y
  val towerType = tower_type
  var damage = tower_type.damage
  var rangeInTiles = tower_type.rangeInTiles
  var firingSpeed = tower_type.firingSpeed

  val attack: () => Boolean = tower_type.attack_from(this, gamestate)

  def update(delta: Double): Unit = ???

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

  def clone(x: Double, y: Double): Tower = {
    new Tower(tower_type, x, y, gamestate)
  }
}
