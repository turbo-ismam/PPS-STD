package Controller

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.{Easy, Enemy, WaveImpl}
import Model.Player
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Model.Tower.{TowerType, TowerTypes}
import scalafx.animation.AnimationTimer
import scalafx.print.PrintColor.Color
import scalafx.scene.paint.Color.Red

import scala.collection.mutable.Map
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
  val available_towers: Map[TowerTypes.TowerType, Tower] = Map.empty[TowerTypes.TowerType, Tower]
  var selected_tower: Option[Tower] = None
  var selected_cell: Option[Tower] = None
  var wave_counter = 0
  var release_selected_cell_and_tower: Boolean = false
  val framerate = 1.0 / 30.0 * 1000
  val wave = new WaveImpl(1, this)
  var lastTime = 0L

  /**
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

  def onPlayButton(): Unit = {
    logger.info("Started wave")
    wave_counter += 1
    wave.populate(3, Easy, gridController.getGameMap)
  }

  def resetSelectedTower(): Unit = {
    selected_tower = None
  }

  def update(delta: Double): Unit = {
    if (alive) {
      towers.foreach(tower => tower.update(delta))
      DrawingManager.drawGrid(this)
      enemies.foreach(enemy => {
        enemy.update(delta)
        val x = enemy.enemyCurrentPosition().x
        val y = enemy.enemyCurrentPosition().y
        DrawingManager.enemyDraw(x, y, Red)
      })
      wave.update(delta)
      if (player.health <= 0) {
        alive = false
        logger.info("Player {} lose the game ", player.playerName)
        logger.info("Player {} stats : \n kill counter: {} ", player.killCounter)
      }
    }
  }

  def run(): AnimationTimer = {
    logger.info("Start tower defense game")

    //Animation timer and the time of the game.
    var lastTime = 0L

    val timer = AnimationTimer { t =>
      if (lastTime != 0) {
        val delta = (t - lastTime) / 1e9 //In seconds.
        update(delta)
      }
      lastTime = t
    }
    timer
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
    available_towers ++= List(
      BASE_TOWER -> new Tower(TowerType(BASE_TOWER), player, 0, 0, this),
      CANNON_TOWER -> new Tower(TowerType(CANNON_TOWER), player, 0, 0, this),
      FLAME_TOWER -> new Tower(TowerType(FLAME_TOWER), player, 0, 0, this)
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
    towers.foreach(tower => if (tower.posX == x && tower.posY == y) false)
    true
  }

}

object GameController {

  private var _game_controller: Option[GameController] = None

  def game_controller: Option[GameController] = _game_controller

  private def game_controller_=(gameController: Option[GameController]): Unit = {
    _game_controller = gameController
  }

  def apply(playerName: String, mapDifficulty: Int): GameController = {
    val gameController: GameController = new GameController(playerName, mapDifficulty)
    gameController.setupAvailableTowers()
    game_controller = Option(gameController)
    gameController
  }
}
