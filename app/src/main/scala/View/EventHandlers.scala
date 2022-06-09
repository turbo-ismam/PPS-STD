package View

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{GENERIC_GOOD_EXIT_STATUS, NOTHING_MESSAGE, STAGE_ERROR}
import Controller.{DrawingManager, GameController}
import Logger.LogHelper
import Model.Tower.TowerTypes
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Utility.Utils
import View.ViewController.{GameViewController, MainMenuViewController}
import javafx.event.{ActionEvent, EventHandler}
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{ComboBox, TextField}

object EventHandlers extends LogHelper {

  def startGame(primaryStage: Option[PrimaryStage], playerNameTextField: TextField, difficultyComboBox: ComboBox[String]): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      primaryStage match {
        case None => logger.error(STAGE_ERROR)
        case Some(primaryStage: PrimaryStage) =>
          val textFieldValue: String = playerNameTextField.text.value
          val playerName: String = if (textFieldValue.isEmpty) Utils.getRandomName() else textFieldValue
          val difficultChoice = Utils.mapGameDifficult(difficultyComboBox.getSelectionModel.getSelectedItem)

          //Generate new game Controller
          val gameController: GameController = GameController.apply(playerName, difficultChoice)

          logger.info("Initialize game: \n Player name = {} \n Difficult choice = {}", playerName, difficultChoice)

          val gameViewController: GameViewController = GameViewController.apply(primaryStage)
          primaryStage.setScene(gameViewController.gameViewModel().gameScene())

          DrawingManager.drawGrid(gameController)
      }
    }
  }

  def goMainMenu(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      primaryStage match {
        case None => logger.error(STAGE_ERROR)
        case Some(primaryStage: PrimaryStage) =>
          val mainMenuViewController: MainMenuViewController = MainMenuViewController.apply(primaryStage)
          primaryStage.setScene(mainMenuViewController.menuViewModel().menuScene())
      }
    }
  }

  def selectTower(towerTypes: TowerTypes.TowerType): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      val gameController: GameController = GameController.game_controller.get
      towerTypes match {
        case BASE_TOWER =>
          val tower = gameController.available_towers.get(BASE_TOWER)
          gameController.selected_tower = tower
          logger.info("Select tower {} ", tower.get.name())
        case CANNON_TOWER =>
          val tower = gameController.available_towers.get(CANNON_TOWER)
          gameController.selected_tower = tower
          logger.info("Select tower {} ", tower.get.name())
        case FLAME_TOWER =>
          val tower = gameController.available_towers.get(FLAME_TOWER)
          gameController.selected_tower = tower
          logger.info("Select tower {} ", tower.get.name())
        case _ =>
          logger.warn("Non implemented yet")
      }
    }
  }

  def exitGame(): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      System.exit(GENERIC_GOOD_EXIT_STATUS)
    }
  }

  def nothing(): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      logger.error(NOTHING_MESSAGE)
    }
  }
}
