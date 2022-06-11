package View.ViewModel

import Configuration.DefaultConfig.{ADD_MAP_BTN, ADD_MAP_BTN_ID, DIFFICULTY_COMBO_BOX_EASY, DIFFICULTY_COMBO_BOX_HARD, DIFFICULTY_COMBO_BOX_ID, DIFFICULTY_COMBO_BOX_NORMAL, EXIT_GAME_BTN, EXIT_GAME_BTN_ID, GAME_WINDOW_HEIGHT, GAME_WINDOW_WIDTH, START_GAME_BTN, START_GAME_BTN_ID, UPLOAD_MAP_TEXT_BOX}
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox, TextField}
import scalafx.scene.layout.{BorderPane, HBox, VBox}

/**
 * Model of the main menu scene
 * It contains only scene view definitions
 */
class MainMenuViewModel {

  private val _startGameButton: Button = new Button {
    prefWidth = 150
    prefHeight = 50
    text = START_GAME_BTN
    id = START_GAME_BTN_ID
  }

  private val _difficultyComboBox = new ComboBox[String] {
    prefWidth = 150
    prefHeight = 50
    items = ObservableBuffer(DIFFICULTY_COMBO_BOX_EASY, DIFFICULTY_COMBO_BOX_NORMAL, DIFFICULTY_COMBO_BOX_HARD)
    id = DIFFICULTY_COMBO_BOX_ID
  }

  private val _exitGameButton: Button = new Button {
    prefWidth = 150
    prefHeight = 50
    text = EXIT_GAME_BTN
    id = EXIT_GAME_BTN_ID
  }

  private val _playerNameTextField: TextField = new TextField() {
    minWidth = 300
    prefHeight = 50
    promptText = "Player name"
  }

  private val _uploadedMapPathTextField: TextField = new TextField() {
    minWidth = 300
    prefHeight = 50
    promptText = UPLOAD_MAP_TEXT_BOX
  }

  private val _addMapButton: Button = new Button {
    prefWidth = 150
    prefHeight = 50
    text = ADD_MAP_BTN
    id = ADD_MAP_BTN_ID
  }

  private val _infoBox = new HBox {
    padding = Insets(10)
    spacing = 10
    alignment = Pos.Center
    children = List(
      _difficultyComboBox,
      _playerNameTextField
    )
  }

  private val _customMapBox = new HBox {
    padding = Insets(10)
    spacing = 10
    alignment = Pos.Center
    children = List(
      _addMapButton,
      _uploadedMapPathTextField
    )
  }

  private val _infoCustomBox = new VBox {
    padding = Insets(10)
    spacing = 10
    alignment = Pos.Center
    children = List(
      _infoBox,
      _customMapBox
    )
  }

  private val _optionsVBox = new VBox {
    padding = Insets(10)
    spacing = 10
    alignment = Pos.Center
    children = List(
      _startGameButton,
      _exitGameButton
    )
  }



  private val _menuScene: Scene = new Scene {
    root = new BorderPane {
      padding = Insets(300,200,100,200)
      prefWidth = GAME_WINDOW_WIDTH
      prefHeight = GAME_WINDOW_HEIGHT
      top = _infoCustomBox
      center = _optionsVBox

    }
  }

  def options(): VBox = _optionsVBox

  def menuScene(): Scene = _menuScene

  def buttons(): List[Button] = List(_startGameButton, _addMapButton, _exitGameButton)

  def comboBox(): ComboBox[String] = _difficultyComboBox

  def playerNameTextField(): TextField = _playerNameTextField

  def uploadedMapPathTextField(): TextField = _uploadedMapPathTextField
}

object MainMenuViewModel {

  def apply(): MainMenuViewModel = new MainMenuViewModel()
}
