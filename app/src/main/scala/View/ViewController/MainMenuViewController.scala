package View.ViewController

import Configuration.DefaultConfig.{ADD_MAP_BTN_ID, EXIT_GAME_BTN_ID, START_GAME_BTN_ID}
import View.EventHandlers.MainMenuEventHandlers
import View.ViewModel.MainMenuViewModel
import scalafx.application.JFXApp3.PrimaryStage

/**
 * This class is the controller of the Main Menu Scene
 * From here a player can:
 * 1. Start a new game
 * 2. Set the difficulty level
 * 3. Add a custom map from file system
 * 4. Exit from the game
 */
trait MainMenuViewController extends ViewModelController {

  def menuViewModel: MainMenuViewModel
}

object MainMenuViewController {

  private sealed case class MainMenuViewControllerImpl() extends MainMenuViewController {

    private val _gameViewModel: MainMenuViewModel = MainMenuViewModel()

    private val mainMenuEventHandlers: MainMenuEventHandlers = MainMenuEventHandlers()

    def hookupEvents: Unit = {
      val playerNameTextField = _gameViewModel.playerNameTextField
      _gameViewModel.buttons.foreach(button => {
        button.getId match {
          case START_GAME_BTN_ID => button.setOnAction(mainMenuEventHandlers.startGame(this.primaryStage(),
            playerNameTextField,
            _gameViewModel.DifficultyComboBox,
            _gameViewModel.uploadedMapPathTextField))
          case EXIT_GAME_BTN_ID => button.setOnAction(mainMenuEventHandlers.exitGame)
          case ADD_MAP_BTN_ID => button.setOnAction(mainMenuEventHandlers.openFileChooser(
            _gameViewModel.uploadedMapPathTextField))
        }
      })
    }

    def menuViewModel: MainMenuViewModel = _gameViewModel

  }

  def apply(primaryStage: PrimaryStage): MainMenuViewController = {
    val mainMenuViewController = MainMenuViewControllerImpl()
    mainMenuViewController.primaryStage = primaryStage
    mainMenuViewController.hookupEvents
    mainMenuViewController
  }
}