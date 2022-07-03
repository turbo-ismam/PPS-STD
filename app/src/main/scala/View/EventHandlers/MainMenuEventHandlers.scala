package View.EventHandlers

import Controller.{DrawingManager, GameController, UpdateManager}
import Utility.Cache.TowerDefenseCache
import Utility.Configuration.DefaultConfig._
import Utility.Utils
import Utility.Utils.isJsonFileCheck
import View.ViewController.GameViewController
import javafx.event.{ActionEvent, EventHandler}
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{ComboBox, TextField}
import scalafx.stage.FileChooser

/**
 * This class contains all event handler that is used from the Main Menu Controller
 * Handle standard operations like:
 * 1. Start the game
 * 2. Open the file chooser to choose a file (to generate a custom grid)
 * 3. Quit from the application
 */
trait MainMenuEventHandlers extends EventHandlers {

  /**
   * Handle the click on the start game button, it start a new game with the inserted specification, if the player
   * does not select anything the application will use the default settings
   *
   * @param primaryStage             of the game
   * @param playerNameTextField      inserted by the user or will be used a default one
   * @param difficultyComboBox       difficulty level if is selected, otherwise will be used the default difficulty level
   * @param uploadedMapPathTextField if is specified the difficulty combo box is overridden with the custom map path
   * @return action listener that handle the event
   */
  def startGame(primaryStage: Option[PrimaryStage],
                playerNameTextField: TextField,
                difficultyComboBox: ComboBox[String],
                uploadedMapPathTextField: TextField): EventHandler[ActionEvent]

  /**
   * Handle the file chooser opening, it open a file chooser to select a custom game map from the file system
   *
   * @param textField where the result of the selection will be written (the path of the selected map)
   * @return action listener that handle the event
   */
  def openFileChooser(textField: TextField): EventHandler[ActionEvent]

  /**
   * Handle the click on the exit game button, it just exit from the game
   *
   * @return action listener that handle the event
   */
  def exitGame: EventHandler[ActionEvent]

}

object MainMenuEventHandlers {

  sealed private case class MainMenuEventHandlersImpl() extends MainMenuEventHandlers {

    override def startGame(primaryStage: Option[PrimaryStage],
                           playerNameTextField: TextField,
                           difficultyComboBox: ComboBox[String],
                           uploadedMapPathTextField: TextField): EventHandler[ActionEvent] = {
      (_: ActionEvent) => {

        primaryStage match {

          case None => logger.error(STAGE_ERROR)
          case Some(primaryStage: PrimaryStage) =>

            val textFieldValue: String = playerNameTextField.text.value
            val playerName: String = if (textFieldValue.isEmpty) Utils.getRandomName() else textFieldValue
            TowerDefenseCache.playerName = playerName

            val difficultChoice = Utils.mapGameDifficult(difficultyComboBox.getSelectionModel.getSelectedItem)
            TowerDefenseCache.difficulty = difficultChoice

            val externalMapPath = uploadedMapPathTextField.getText()

            var gameController: GameController = null

            if (externalMapPath == null || !isJsonFileCheck(externalMapPath)) {
              gameController = GameController.apply(playerName, difficultChoice)
              TowerDefenseCache.gameType = true
            } else {
              TowerDefenseCache.loadedMap_=(externalMapPath)
              gameController = GameController.apply(playerName, 0)
              TowerDefenseCache.gameType = false
            }

            val gameViewController: GameViewController = GameViewController.apply(primaryStage, gameController)

            setScene(primaryStage, gameViewController, playerName, difficultChoice)

            DrawingManager.drawGrid(gameController, gameViewController)
            //start loop
            UpdateManager.apply(gameController, gameViewController).run().start()
        }
      }
    }

    override def openFileChooser(textField: TextField): EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        val fileChooser: FileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File")
        fileChooser.showOpenDialog(new PrimaryStage) match {
          case null => logger.warn("File not selected")
          case fileName =>
            textField.setText("/" + fileName.toString.replace("\\", "/")
              .replace("%20", " "))
            if (isJsonFileCheck(fileName.toString)) TowerDefenseCache.loadedMap = fileName.toString
            else textField.setText("Attention, selected file isn't a JSON file!")
        }
      }
    }

    override def exitGame: EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        System.exit(GENERIC_GOOD_EXIT_STATUS)
      }
    }

  }

  def apply(): MainMenuEventHandlers = MainMenuEventHandlersImpl()
}
