package Controller

import Controller.Tower.Tower
import Controller.Wave.Wave
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Grid.GridController
import Model.Player
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Model.Tower.{TowerType, TowerTypes}
import Utility.WayPoint

import scala.collection.mutable.{ListBuffer, Map}

/**
 * This class is the main controller, here is declared all sub-entities controller
 *
 * @param playerName    player nickname
 * @param mapDifficulty difficulty level of the game
 */
class GameController(playerName: String, mapDifficulty: Int) extends LogHelper {

  private val _gridController: GridController = GridController(mapDifficulty)
  private val _player: Player = Player(playerName)
  private val _towers = new ListBuffer[Tower]
  private val _enemies = new ListBuffer[Enemy]
  private var _gameStarted = false
  private val _availableTowers: Map[TowerTypes.TowerType, Tower] = Map.empty[TowerTypes.TowerType, Tower]
  private var _selectedTower: Option[Tower] = None
  private val _waveScheduler: WaveScheduler = WaveScheduler()
  private var _wave: Wave = new Wave(0, this)
  private val _junkEnemies = new ListBuffer[Enemy]

  def onCellClicked(x: Double, y: Double): Unit = {
    if (isTowerSelected &&
      isTileBuildable((x / 64).toInt, (y / 64).toInt)
      && playerHaveEnoughMoneyEnough) {

      val xPos: Int = (x / 64).toInt * 64
      val yPos: Int = (y / 64).toInt * 64

      if (!isAnotherTowerInTile(xPos, yPos)) {
        val tower = selectedTower.get.clone(WayPoint(xPos, yPos))
        this += tower
        selectedTower = Option(tower)
      } else {
        logger.warn("There is already a tower in that position")
      }
    } else if (isTowerSelected && !playerHaveEnoughMoneyEnough) {
      logger.info("Not enough money! Current money= " + player.money)
    } else if (!isTowerSelected && isAnotherTowerInTile(x.toInt, y.toInt)) {
      logger.info("No tower selected, but some tower is already in this position ")
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
    selectedTower = None
  }

  def buildTower(tower: Tower): Unit = {
    selectedTower = Option(tower)
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

  def initializeTower(): Unit = {
    availableTowers ++= List(
      BASE_TOWER -> new Tower(TowerType(BASE_TOWER), player, WayPoint(0, 0), this),
      CANNON_TOWER -> new Tower(TowerType(CANNON_TOWER), player, WayPoint(0, 0), this),
      FLAME_TOWER -> new Tower(TowerType(FLAME_TOWER), player, WayPoint(0, 0), this)
    )
  }

  def gridController: GridController = _gridController

  def enemies: ListBuffer[Enemy] = _enemies

  def towers: ListBuffer[Tower] = _towers

  def player: Player = _player

  def availableTowers: Map[TowerTypes.TowerType, Tower] = _availableTowers

  def selectedTower: Option[Tower] = _selectedTower

  def gameStarted: Boolean = _gameStarted

  def waveScheduler: WaveScheduler = _waveScheduler

  def wave: Wave = _wave

  def junkEnemies: ListBuffer[Enemy] = _junkEnemies

  def gameStarted_=(newGameStatus: Boolean): Unit = {
    _gameStarted = newGameStatus
  }

  def selectedTower_=(selectedTower: Option[Tower]): Unit = {
    _selectedTower = selectedTower
  }

  def wave_=(wave: Wave): Unit = {
    _wave = wave
  }

  def removeEnemy(enemy: Enemy): Unit = {
    junkEnemies += enemy
  }

  private def isTowerSelected: Boolean = if (selectedTower.isEmpty) false else true

  private def playerHaveEnoughMoneyEnough: Boolean = player.removeMoney(selectedTower.get.price)

  private def isTileBuildable(x: Int, y: Int): Boolean = gridController.isTileBuildable(x, y)

  private def isAnotherTowerInTile(x: Int, y: Int): Boolean = {
    towers.foreach(tower => {
      if (tower.towerPosition.x == x && tower.towerPosition.y == y) return true
    })
    false
  }

}

object GameController {

  def apply(playerName: String, mapDifficulty: Int): GameController = {
    val gameController: GameController = new GameController(playerName, mapDifficulty)
    gameController.initializeTower()
    gameController
  }
}
