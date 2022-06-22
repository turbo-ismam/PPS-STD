package View.EventHandlers

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{GENERIC_GOOD_EXIT_STATUS, NOTHING_MESSAGE, STAGE_ERROR}
import Controller.{DrawingManager, GameController, UpdateManager}
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
class MainMenuEventHandlers private extends AbstractEventHandlers {

  def startGame(primaryStage: Option[PrimaryStage],
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

          setScene(primaryStage,gameViewController,playerName,difficultChoice)

          DrawingManager.drawGrid(gameController, gameViewController)
          //start loop
          UpdateManager.apply(gameController, gameViewController).run().start()
      }
    }
  }

  def openFileChooser(textField: TextField): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      val fileChooser: FileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File")
      fileChooser.showOpenDialog(new PrimaryStage) match {
        case null => logger.warn("File not selected")
        case fileName =>
          textField.setText("/" + fileName.toString.replace("\\", "/")
            .replace("%20", " "))
          if (isJsonFileCheck(fileName.toString)) TowerDefenseCache.loadedMap_=(fileName.toString)
          else textField.setText("Attention, selected file isn't a JSON file!")
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

object MainMenuEventHandlers {

  def apply(): MainMenuEventHandlers = new MainMenuEventHandlers()
}
