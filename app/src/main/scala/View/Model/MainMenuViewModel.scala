package View.Model

import Configuration.DefaultConfig.{DIFFICULTY_COMBO_BOX_EASY, DIFFICULTY_COMBO_BOX_HARD, DIFFICULTY_COMBO_BOX_ID, DIFFICULTY_COMBO_BOX_NORMAL, EXIT_GAME_BTN, EXIT_GAME_BTN_ID, GAME_WINDOW_HEIGHT, GAME_WINDOW_WIDTH, OPTIONS_PADDING, OPTIONS_SPACING, START_GAME_BTN, START_GAME_BTN_ID}
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox}
import scalafx.scene.layout.{BorderPane, VBox}

/**
 * Model of the main menu scene
 * It contains only scene view definitions
 */
class MainMenuViewModel {

  private val _startGameButton: Button = new Button {
    text = START_GAME_BTN
    id = START_GAME_BTN_ID
  }

  private val _difficultyComboBox = new ComboBox[String] {
    items = ObservableBuffer(DIFFICULTY_COMBO_BOX_EASY,DIFFICULTY_COMBO_BOX_NORMAL,DIFFICULTY_COMBO_BOX_HARD)
    id = DIFFICULTY_COMBO_BOX_ID
  }

  private val _exitGameButton: Button = new Button {
    text = EXIT_GAME_BTN
    id = EXIT_GAME_BTN_ID
  }

  private val _optionsVBox = new VBox {
    padding = Insets(OPTIONS_PADDING)
    spacing = OPTIONS_SPACING
    alignment = Pos.Center
    children = List(
      _startGameButton,
      _difficultyComboBox,
      _exitGameButton
    )
  }

  private val _menuScene: Scene = new Scene {
    root = new BorderPane {
      prefWidth = GAME_WINDOW_WIDTH
      prefHeight = GAME_WINDOW_HEIGHT
      center = _optionsVBox
    }
  }

  def options(): VBox = _optionsVBox

  def menuScene(): Scene = _menuScene

  def buttons(): List[Button] = List(_startGameButton, _exitGameButton)

  def comboBox(): ComboBox[String] = _difficultyComboBox
}

object MainMenuViewModel {

  def apply(): MainMenuViewModel = new MainMenuViewModel()
}
