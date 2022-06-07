package View

import Configuration.DefaultConfig.{GENERIC_GOOD_EXIT_STATUS, NOTHING_MESSAGE, STAGE_ERROR}
import Logger.LogHelper
import Controller.{DrawingManager, GameController}
import View.ViewController.{GameViewController, MainMenuViewController}
import ViewTMP.GameView.gameController
import javafx.event.{ActionEvent, EventHandler}
import scalafx.application.JFXApp3.PrimaryStage

object EventHandlers extends LogHelper{

  def startGame(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      primaryStage match {
        case None => logger.error(STAGE_ERROR)
        case Some(primaryStage: PrimaryStage) =>
          val gameViewController: GameViewController = GameViewController.apply(primaryStage)
          primaryStage.setScene(gameViewController.gameViewModel().gameScene())
          gameController = new GameController("onlyForTest", 1)
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
