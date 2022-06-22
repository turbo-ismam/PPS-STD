package View.EventHandlers

import Logger.LogHelper
import View.ViewController.GameViewController
import scalafx.stage.Stage

abstract class AbstractEventHandlers extends LogHelper{

  protected def setScene(stage: Stage, gameViewController: GameViewController, playerName: String, difficulty: Int): Unit = {
    stage.setScene(gameViewController.gameViewModel.gameScene())
    logger.info("Initialize game: \n Player name = {} \n Difficult choice = {}", playerName, difficulty)
  }

}
