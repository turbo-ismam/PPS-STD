package Controller

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.Enemy
import Model.Grid.Grid
import Model.Player

import scala.collection.mutable.ListBuffer

class GameController(playerName: String,
                     mapDifficulty: Int) extends LogHelper {

  val grid: Grid = new Grid(mapDifficulty)
  val player: Player = new Player(playerName)
  val towers = new ListBuffer[Tower]
  val enemies = new ListBuffer[Enemy]
  var alive: Boolean = true
  var gameStarted = false
  var selected_tower: Option[Tower] = None
  var selected_cell: Option[Tower] = None
  var wave_counter = 0

  //Triggered when a map cell is clicked
  def onCellClicked(x: Double, y: Double): Unit = {

  }

  //Triggered when the play button is clicked
  def onPlayButton(): Unit = {
    logger.info("Started game")
    wave_counter += 1
    //Started generate enemies
  }

  //This function represent the step to do on every update
  def update(delta: Double): Unit = {
    if (alive) {
      towers.foreach(tower => tower.update(delta))
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
    //grid += tower
  }

  def -=(tower: Tower): Unit = {
    towers -= tower
    tower.towerType.amount -= 1
    //grid -= tower
  }

}
