package View.ViewController

import Utility.Configuration.DefaultConfig._
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

  /**
   * This method retrieve the model of main menu
   * @return the model of main menu
   */
  def menuViewModel: MainMenuViewModel
}

object MainMenuViewController {

  private sealed case class MainMenuViewControllerImpl() extends MainMenuViewController {

    private val _gameViewModel: MainMenuViewModel = MainMenuViewModel()

    private val mainMenuEventHandlers: MainMenuEventHandlers = MainMenuEventHandlers()

    /**
     * This method hookup the listeners, it is called by the apply in the companion object
     * All the class that implement this trait must be instantiate only from their apply method for this reason
     */
    def hookupEvents(): Unit = {
      val playerNameTextField = _gameViewModel.playerNameTextField
      _gameViewModel.buttons.foreach(button => {
        button.getId match {
          case START_GAME_BTN_ID => button.setOnAction(mainMenuEventHandlers.startGame(this.primaryStage,
            playerNameTextField,
            _gameViewModel.DifficultyComboBox,
            _gameViewModel.uploadedMapPathTextField))
          case EXIT_GAME_BTN_ID => button.setOnAction(mainMenuEventHandlers.exitGame)
          case ADD_MAP_BTN_ID => button.setOnAction(mainMenuEventHandlers.openFileChooser(
            _gameViewModel.uploadedMapPathTextField))
        }
      })
    }

    override def menuViewModel: MainMenuViewModel = _gameViewModel

  }

  def apply(primaryStage: PrimaryStage): MainMenuViewController = {
    val mainMenuViewController = MainMenuViewControllerImpl()
    mainMenuViewController.primaryStage = primaryStage
    mainMenuViewController.hookupEvents()
    mainMenuViewController
  }
}