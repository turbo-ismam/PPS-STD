package View.EventHandlers

import Configuration.DefaultConfig.{NOTHING_MESSAGE, STAGE_ERROR}
import Controller.{DrawingManager, GameController}
import Logger.LogHelper
import Model.Tower.TowerTypes
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import View.ViewController.MainMenuViewController
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.input.MouseEvent
import scalafx.application.JFXApp3.PrimaryStage

class GameEventHandlers extends LogHelper {

  def onCellClickedEventHandler(): EventHandler[MouseEvent] = {
    (event: MouseEvent) => {
      val controller: GameController = GameController.game_controller.get
      if (controller.gameStarted) {
        controller.onCellClicked(event.getX, event.getY)
        //Draw tower on tile
        val tower = controller.selected_tower
        tower match {
          case None => {}
          case Some(tower) =>
            DrawingManager.drawTower(tower.posX, tower.posY, tower.graphic())

        }
      }
    }
  }

  def startWave(): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      val controller: GameController = GameController.game_controller.get
      controller.onPlayButton()
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
          gameController.buildTower(tower.get)
          logger.info("Select tower {} ", tower.get.name())
        case CANNON_TOWER =>
          val tower = gameController.available_towers.get(CANNON_TOWER)
          gameController.buildTower(tower.get)
          logger.info("Select tower {} ", tower.get.name())
        case FLAME_TOWER =>
          val tower = gameController.available_towers.get(FLAME_TOWER)
          gameController.buildTower(tower.get)
          logger.info("Select tower {} ", tower.get.name())
        case _ =>
          logger.warn("Non implemented yet")
      }
    }
  }

  def nothing(): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      logger.error(NOTHING_MESSAGE)
    }
  }

}

object GameEventHandlers {

  def apply(): GameEventHandlers = new GameEventHandlers
}