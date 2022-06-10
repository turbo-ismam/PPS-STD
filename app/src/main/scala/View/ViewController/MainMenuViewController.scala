package View.ViewController

import Configuration.DefaultConfig.{EXIT_GAME_BTN_ID, START_GAME_BTN_ID}
import View.EventHandlers
import View.ModelView.MainMenuViewModel
import scalafx.application.JFXApp3.PrimaryStage

/**
 * This class is the controller of the Main Menu scene
 * From here a player can:
 * 1. Start a new game
 * 2. Set the difficulty level
 * 3. Exit from the game
 */
class MainMenuViewController extends ViewModelController {

  private val _gameViewModel: MainMenuViewModel = MainMenuViewModel.apply()

  private def hookupEvents(): Unit = {
    val playerNameTextField = _gameViewModel.textField()
    val difficultyComboBox = _gameViewModel.comboBox()
    _gameViewModel.buttons().foreach(button => {
      button.getId match {
        case START_GAME_BTN_ID => button.setOnAction(EventHandlers.startGame(this.primaryStage(), playerNameTextField, difficultyComboBox))
        case EXIT_GAME_BTN_ID => button.setOnAction(EventHandlers.exitGame())
      }
    })
    //TODO add the combo box selection handler
  }

  def menuViewModel(): MainMenuViewModel = _gameViewModel
}

object MainMenuViewController {

  private var _menu_view_model: Option[MainMenuViewModel] = None

  def menu_view_model: Option[MainMenuViewModel] = _menu_view_model

  private def menu_view_model_=(menuViewModel: Option[MainMenuViewModel]): Unit = {
    _menu_view_model = menuViewModel
  }

  def apply(primaryStage: PrimaryStage): MainMenuViewController = {
    val mainMenuViewController = new MainMenuViewController()
    menu_view_model = Option(mainMenuViewController.menuViewModel())
    mainMenuViewController.primaryStage_(primaryStage)
    mainMenuViewController.hookupEvents()
    mainMenuViewController
  }
}