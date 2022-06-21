package View.ViewController

import Configuration.DefaultConfig.{GO_MAIN_MENU_BTN_ID, NOT_IMPLEMENTED_YET, RESTART_GAME_BTN_ID, START_WAVE_BTN_ID}
import Controller.GameController
import Logger.LogHelper
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import View.EventHandlers.GameEventHandlers
import View.MusicPlayer
import View.ViewModel.GameViewModel
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.input.MouseEvent

class GameViewController(gameController: GameController) extends ViewModelController with LogHelper {

  private val _gameViewModel: GameViewModel = GameViewModel.apply()
   MusicPlayer.play().start()
  MusicPlayer.play().join()

  private val gameEventHandler: GameEventHandlers = GameEventHandlers.apply(this, gameController)

  def hookupEvents(): Unit = {

    // bottom buttons action listeners
    _gameViewModel.buttons().foreach(button => {
      button.getId match {
        case START_WAVE_BTN_ID => button.setOnAction(gameEventHandler.startWave())
        case GO_MAIN_MENU_BTN_ID => button.setOnAction(gameEventHandler.goMainMenu(this.primaryStage()))
        case RESTART_GAME_BTN_ID => button.setOnAction(gameEventHandler.nothing())
      }
    })

    // tower toggle button action listeners
    _gameViewModel.towerToggleButtons().foreach(toggleButton => {
      toggleButton.getId match {
        case "baseTower" => toggleButton.setOnAction(gameEventHandler.selectTower(BASE_TOWER))
        case "cannonTower" => toggleButton.setOnAction(gameEventHandler.selectTower(CANNON_TOWER))
        case "flameTower" => toggleButton.setOnAction(gameEventHandler.selectTower(FLAME_TOWER))
        case _ => logger.warn(NOT_IMPLEMENTED_YET)
      }
    })

    _gameViewModel.canvas().addEventHandler(MouseEvent.MouseClicked,
      gameEventHandler.onCellClickedEventHandler())
  }

  def gameViewModel: GameViewModel = _gameViewModel
}

object GameViewController {

  def apply(primaryStage: PrimaryStage, gameController: GameController): GameViewController = {
    val gameViewController = new GameViewController(gameController)
    gameViewController.primaryStage_(primaryStage)
    gameViewController.hookupEvents()
    gameViewController
  }
}
