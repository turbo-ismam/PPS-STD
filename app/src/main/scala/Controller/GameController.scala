package Controller

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Player
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Model.Tower.{TowerType, TowerTypes}

import scala.collection.mutable.ListBuffer

/**
 * This class is the main controller, here is declared all sub-entities controller
 *
 * @param playerName    the player nickname
 * @param mapDifficulty difficulty level of the game
 */
class GameController(playerName: String, mapDifficulty: Int) extends LogHelper {

  private val gridController: GridController = new GridController(mapDifficulty)
  val player: Player = new Player(playerName)
  var towers = new ListBuffer[Tower]
  var enemies = new ListBuffer[Enemy]
  var alive: Boolean = true
  val gameStarted = false
  //Available tower ready to use by player
  var available_towers: Map[TowerTypes.TowerType , Tower] = Map.empty[TowerTypes.TowerType, Tower]
  var selected_tower: Option[Tower] = None
  var selected_cell: Option[Tower] = None
  var wave_counter = 0
  var release_selected_cell_and_tower: Boolean = false
  val framerate = 1.0 / 30.0 * 1000

  /**
   * This method check if all condition to create a new tower is respected
   * Check if the tower is selected
   * Check if the money is enough to build the selected tower
   * Check if there is another tower in the clicked tile
   * Check if the selected tile is a buildable tile
   *
   * @param x longitude of selected tile
   * @param y latitude of selected tile
   */
  def onCellClicked(x: Double, y: Double): Unit = {
    if (isTowerSelected &&
      isTileBuildable(x.toInt, y.toInt)
      && playerHaveEnoughMoneyEnough) {
      this += selected_tower.get.clone(x, y)
    } else if (isTowerSelected && !playerHaveEnoughMoneyEnough) {
      logger.info("Not enough money! Current money= " + player.money)
    } else if (!isTowerSelected && isAnotherTowerInTile(x.toInt, y.toInt)) {
      selected_cell = Some(towers.filter((_.posX.toInt == x.toInt))
        .filter((_.posY.toInt == y.toInt)).head)
    }
  }

  //Triggered when the play button is clicked
  def onPlayButton(): Unit = {
    logger.info("Started game")
    wave_counter += 1
    //Started generate enemies
  }

  def resetSelectedTower(): Unit = {
    selected_tower = None
  }

  //This function represent the step to do on every update
  def update(delta: Double): Unit = {
    towers.foreach(tower => tower.update(delta))
    enemies.foreach(enemy => enemy.update(delta))
  }

  def run(): Unit = {
    logger.info("Start tower defense game")
    var delta: Double = 0.0
    while (true) {
      val start = System.currentTimeMillis()
      update(delta)
      if (release_selected_cell_and_tower)
        resetSelectedTower()

      val milliseconds = framerate.toInt - (System.currentTimeMillis() - start)
      Thread.sleep(milliseconds)
      delta = (System.currentTimeMillis() - start).toDouble / 1000

      if (player.health <= 0) {
        logger.info("Player {} lose the game ", player.playerName)
        logger.info("Player {} stats : \n kill counter: {} ", player.killCounter)
        return
      }
    }
  }

  def buildTower(tower: Tower): Unit = {
    selected_tower = Option(tower)
  }

  def sellTower(tower: Tower): Unit = {
    tower.player.addMoney(tower.sellCost())
    towers -= tower
  }

  //Enemies
  def +=(enemy: Enemy): Unit = {
    enemies += enemy
  }

  def -=(enemy: Enemy): Unit = {
    enemies -= enemy
  }

  def +=(tower: Tower): Unit = {
    towers += tower
    tower.towerType.amount += 1
  }

  def -=(tower: Tower): Unit = {
    towers -= tower
    tower.towerType.amount -= 1
  }

  def setupAvailableTowers(): Unit = {
    available_towers = available_towers + (
      BASE_TOWER -> new Tower(TowerType(BASE_TOWER), player, 0, 0, this),
      CANNON_TOWER -> new Tower(TowerType(CANNON_TOWER), player, 0, 0, this),
      FLAME_TOWER -> new Tower(TowerType(FLAME_TOWER), player, 0, 0, this)
    )
  }

  def addNewTowerToCache(towerType : TowerTypes.TowerType, tower: Tower): Unit = {
    available_towers = available_towers +  {
      towerType -> tower
    }
  }

  def getGridController: GridController = this.gridController

  private def isTowerSelected: Boolean = if (selected_tower.isEmpty) false else true

  private def playerHaveEnoughMoneyEnough: Boolean = player.removeMoney(selected_tower.get.price())

  private def isTileBuildable(x: Int, y: Int): Boolean = gridController.isTileBuildable(x, y)

  private def isAnotherTowerInTile(x: Int, y: Int): Boolean = {
    towers.foreach(tower => if (tower.posX == x && tower.posY == y) false)
    true
  }

}
