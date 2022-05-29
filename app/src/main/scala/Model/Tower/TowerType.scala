package Model.Tower

import Controller.Tower.Tower
import Model.Enemy.Enemy
import Model.Tower.Exceptions.TowerNotExistException

import scala.collection.mutable.Buffer
import java.io.File
import javax.imageio.ImageIO


object TowerType {
  val BASE_TOWER = 1
  val CANNON_TOWER = 2
  val FLAME_TOWER = 3

  def deserialize(value: Int) : TowerType = {
    value match {
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
}

/**
 * A trait that defines the type of tower. Every tower inherits this trait.
 */
trait TowerType {
  val name = "Tower"
  val desc = "A basic tower description"
  val tower_graphic = ImageIO.read(new File(getClass().getResource("/towers/base_tower.png").getPath()))
  val projectile_graphic = ImageIO.read(new File(getClass().getResource("/projectiles/base_projectile.png").getPath()))
  val size  = 1 /*Size in tiles*/
  var enemies = Buffer[Enemy]()
  var base_damage = 5
  var damage = 5 /* Default damage, but can increase */
  var base_rangeInTiles = 5
  var rangeInTiles = 5 /* Range in tiles */
  var firingSpeed = 4
  var price = 50

  def attack_from(tower: Tower, gameState: Any): () => Boolean = { () => true}

  def draw(): Unit
  def serialize(): Int
}