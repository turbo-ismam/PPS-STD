package View.EventHandlers

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{GENERIC_GOOD_EXIT_STATUS, NOTHING_MESSAGE, STAGE_ERROR}
import Controller.{DrawingManager, GameController}
import Logger.LogHelper
import Utility.Utils
import View.ViewController.GameViewController
import javafx.event.{ActionEvent, EventHandler}
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.control.{ComboBox, TextField}
import scalafx.stage.FileChooser

class MainMenuEventHandlers extends LogHelper {

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
          val difficultChoice = Utils.mapGameDifficult(difficultyComboBox.getSelectionModel.getSelectedItem)

          val gameViewController: GameViewController = GameViewController.apply(primaryStage)
          primaryStage.setScene(gameViewController.gameViewModel.gameScene())

          val externalMapPath = uploadedMapPathTextField.getText()
          var gameController: GameController = null

          if (externalMapPath == null || !isJsonFileCheck(externalMapPath)) {
            gameController = GameController.apply(playerName, difficultChoice, gameViewController)
          } else {
            TowerDefenseCache.loadedMap_(externalMapPath)
            gameController = GameController.apply(playerName, 0, gameViewController)
          }

          logger.info("Initialize game: \n Player name = {} \n Difficult choice = {}", playerName, difficultChoice)


          DrawingManager.drawGrid(gameController)
          //start loop
          gameController.run().start()
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
          textField.setText("/" + fileName.toString.replace("\\", "/").replace("%20", " "))
          if (isJsonFileCheck(fileName.toString)) TowerDefenseCache.loadedMap_(fileName.toString)
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

  private def isJsonFileCheck(fileNamePath: String): Boolean = {
    if (fileNamePath.endsWith(".json")) true else false
  }
}

object MainMenuEventHandlers {

  def apply(): MainMenuEventHandlers = new MainMenuEventHandlers()
}
