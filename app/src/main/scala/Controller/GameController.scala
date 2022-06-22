package Controller

import Controller.Tower.Tower
import Controller.Wave.WaveImpl
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Grid.GridController
import Model.Player
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Model.Tower.{TowerType, TowerTypes}
import Utility.WayPoint
import View.ViewController.GameViewController
import scalafx.animation.AnimationTimer

import scala.collection.mutable.{ListBuffer, Map}

/**
 * This class is the main controller, here is declared all sub-entities controller
 *
 * @param playerName    the player nickname
 * @param mapDifficulty difficulty level of the game
 */
class GameController(playerName: String, mapDifficulty: Int) extends LogHelper {

  private val gridController: GridController = GridController(mapDifficulty)
  val player: Player = new Player(playerName)
  val towers = new ListBuffer[Tower]
  val enemies = new ListBuffer[Enemy]
  val toRemoveEnemies = new ListBuffer[Enemy]
  var gameStarted = false
  //Available tower ready to use by player
  val available_towers: Map[TowerTypes.TowerType, Tower] = Map.empty[TowerTypes.TowerType, Tower]
  var selected_tower: Option[Tower] = None
  var selected_cell: Option[Tower] = None
  val waveScheduler: WaveScheduler = WaveScheduler.apply()
  var wave: WaveImpl = new WaveImpl(0, this)
  var firstWave: Boolean = true

  /**
   * @param x longitude of selected tile
   * @param y latitude of selected tile
   */
  def onCellClicked(x: Double, y: Double): Unit = {
    if (isTowerSelected &&
      isTileBuildable((x / 64).toInt, (y / 64).toInt)
      && playerHaveEnoughMoneyEnough) {

      val xPos: Int = (x / 64).toInt * 64
      val yPos: Int = (y / 64).toInt * 64

      val tower = selected_tower.get.clone(WayPoint(xPos, yPos))
      this += tower
      selected_tower = Option(tower)
    } else if (isTowerSelected && !playerHaveEnoughMoneyEnough) {
      logger.info("Not enough money! Current money= " + player.money)
    } else if (!isTowerSelected && isAnotherTowerInTile(x.toInt, y.toInt)) {
      selected_cell = Some(towers.filter((_.posX.toInt == x.toInt))
        .filter((_.posY.toInt == y.toInt)).head)
    } else if (!isTowerSelected) {
      logger.info("No tower selected")
    }
  }

  def onPlayButton(): Unit = {
    logger.info("Started wave")
    gameStarted = true
    waveScheduler.firstWave = true
    wave = waveScheduler.start(wave)
  }

  def resetSelectedTower(): Unit = {
    selected_tower = None
  }

  def buildTower(tower: Tower): Unit = {
    selected_tower = Option(tower)
  }

  def sellTower(tower: Tower): Unit = {
    tower.player.addMoney(tower.price())
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

  def addToRemoveEnemy(enemy: Enemy): Unit = {
    toRemoveEnemies += enemy
  }

  def setupAvailableTowers(): Unit = {
    available_towers ++= List(
      BASE_TOWER -> new Tower(TowerType(BASE_TOWER), player, WayPoint(0, 0), this),
      CANNON_TOWER -> new Tower(TowerType(CANNON_TOWER), player, WayPoint(0, 0), this),
      FLAME_TOWER -> new Tower(TowerType(FLAME_TOWER), player, WayPoint(0, 0), this)
    )
  }

  def addNewTowerToCache(towerType: TowerTypes.TowerType, tower: Tower): Unit = {
    available_towers.addOne(towerType -> tower)
  }

  def getGridController: GridController = this.gridController

  private def isTowerSelected: Boolean = if (selected_tower.isEmpty) false else true

  private def playerHaveEnoughMoneyEnough: Boolean = player.removeMoney(selected_tower.get.price())

  private def isTileBuildable(x: Int, y: Int): Boolean = gridController.isTileBuildable(x, y)

  private def isAnotherTowerInTile(x: Int, y: Int): Boolean = {
    var tower_in_tile = false
    towers.foreach(tower => {
      if (tower.posX == x && tower.posY == y)
        tower_in_tile = false
      else
        tower_in_tile = true
    })
    tower_in_tile
  }

}

object GameController {

  def apply(playerName: String, mapDifficulty: Int): GameController = {
    val gameController: GameController = new GameController(playerName, mapDifficulty)
    gameController.setupAvailableTowers()
    gameController
  }
}
