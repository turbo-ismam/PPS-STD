package View.ViewController

import Configuration.DefaultConfig.{GO_MAIN_MENU_BTN_ID, NOT_IMPLEMENTED_YET, RESTART_GAME_BTN_ID, START_WAVE_BTN_ID}
import Logger.LogHelper
import View.EventHandlers
import View.Model.GameViewModel
import scalafx.application.JFXApp3.PrimaryStage

class GameViewController extends ViewModelController with LogHelper{

  private val _gameViewModel: GameViewModel = GameViewModel.apply()

  def hookupEvents(): Unit = {

    // bottom buttons action listeners
    _gameViewModel.buttons().foreach(button => {
      button.getId match {
        case START_WAVE_BTN_ID => button.setOnAction(EventHandlers.nothing())
        case GO_MAIN_MENU_BTN_ID => button.setOnAction(EventHandlers.goMainMenu(this.primaryStage()))
        case RESTART_GAME_BTN_ID => button.setOnAction(EventHandlers.nothing())
      }
    })

    // tower toggle button action listeners
    _gameViewModel.towerToggleButtons().foreach(toggleButton => {
      toggleButton.getId match {
        case "baseTower" => toggleButton.setOnAction(EventHandlers.nothing())
        case "cannonTower" => toggleButton.setOnAction(EventHandlers.nothing())
        case "flameTower" => toggleButton.setOnAction(EventHandlers.nothing())
        case _ => logger.warn(NOT_IMPLEMENTED_YET)
      }
    })
  }

  def gameViewModel(): GameViewModel = _gameViewModel
}

object GameViewController {

  def apply(primaryStage: PrimaryStage): GameViewController = {
    val gameViewController = new GameViewController()
    gameViewController.primaryStage_(primaryStage)
    gameViewController.hookupEvents()
    gameViewController
  }
}
