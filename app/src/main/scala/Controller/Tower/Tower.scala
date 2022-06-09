package Controller.Tower

import Controller.GameController
import Model.Player
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
  var charging_counter = 0.0
  var charging_time = tower_type.charging_time

  val attack: () => Boolean = tower_type.attack_from(this, gameController)

  def update(delta: Double): Unit = {
    if (charging_counter <= 0 && attack()) {
      //Reset charging time
      charging_time = tower_type.charging_time
    } else
      charging_time += delta
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

  def sellCost(): Int = {
    tower_type.sell_cost
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
    new Tower(tower_type, player, x, y, gameController)
  }
}
