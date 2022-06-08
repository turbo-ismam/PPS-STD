package Controller

import Cache.TowerDefenseCache
import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.{Easy, Enemy, WaveImpl}
import Model.Player

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
  var selected_tower: Option[Tower] = None
  val selected_cell: Option[Tower] = None
  var wave_counter = 0
  val wave = new WaveImpl(1,this)

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
  def onCellClicked(x: Double, y: Double): Boolean = {
    if (isTowerSelected &&
      isMoneyEnough &&
      isAnotherTowerInTile(x.toInt, y.toInt) &&
      isTileBuildable(x.toInt, y.toInt)) true else false
  }

  //Triggered when the play button is clicked
  def onPlayButton(): Unit = {
    logger.info("Started game")
    wave_counter += 1
    wave.populate(3,Easy,gridController.getGameMap)
    //Started generate enemies
  }

  //This function represent the step to do on every update
  def update(delta: Double): Unit = {
    if (alive) {
      towers.foreach(tower => tower.update(delta))
      wave.update(delta)
      //Missing enemy update
      //Missing projectile update
      if (player.health <= 0) {
        alive = false
        logger.info("Player {} lose the game ", player.playerName)
        logger.info("Player {} stats : \n kill counter: {} ", player.killCounter)
        return
      }
    }
  }

  def buildTower(tower: Option[Tower]): Unit = {
    selected_tower = tower
  }

  def buyTower(tower: Tower): Unit = {
    tower.player.updateMoney(tower.price(), true)
    towers += tower
  }

  def sellTower(tower: Tower): Unit = {
    tower.player.updateMoney(tower.sellCost(), false)
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
    //Need to define function to add tower on map
  }

  def -=(tower: Tower): Unit = {
    towers -= tower
    tower.towerType.amount -= 1
  }

  def getGridController: GridController = this.gridController

  private def isTowerSelected: Boolean = if (TowerDefenseCache.selectedTower.isEmpty) false else true

  private def isMoneyEnough: Boolean = true // TODO idk how to check money -ismam

  private def isTileBuildable(x: Int, y: Int): Boolean = gridController.isTileBuildable(x, y)

  private def isAnotherTowerInTile(x: Int, y: Int): Boolean = {
    towers.foreach(tower => if (tower.posX == x && tower.posY == y) false)
    true
  }

}
