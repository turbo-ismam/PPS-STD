package View.Model

import Configuration.DefaultConfig
import Configuration.DefaultConfig.{GAME_CANVAS_HEIGHT, GAME_CANVAS_WIDTH, GAME_WINDOW_HEIGHT, GAME_WINDOW_WIDTH, GO_MAIN_MENU_BTN, GO_MAIN_MENU_BTN_ID, OPTIONS_PADDING, OPTIONS_SPACING, PLAYER_HEALTH_LABEL, PLAYER_HEALTH_LABEL_ID, PLAYER_MONEY_LABEL_ID, Player_MONEY_LABEL, RESTART_GAME_BTN, RESTART_GAME_BTN_ID, START_WAVE_BTN, START_WAVE_BTN_ID}
import Utility.Utils
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.{Button, Label, ToggleButton}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color.Black

class GameViewModel {

  private val _canvas: Canvas = new Canvas(){
    width = GAME_CANVAS_WIDTH
    height = GAME_CANVAS_HEIGHT
  }

  private val _startButton: Button = new Button{
    text = START_WAVE_BTN
    id = START_WAVE_BTN_ID
  }

  private val _closeButton: Button = new Button{
    text = GO_MAIN_MENU_BTN
    id = GO_MAIN_MENU_BTN_ID
  }

  private val _restartButton: Button = new Button{
    text = RESTART_GAME_BTN
    id = RESTART_GAME_BTN_ID
  }

  private val _bottomPane: HBox = {
    new HBox {
      padding = Insets(10)
      spacing = 10
      alignment = Pos.Center
      children = List(
        _startButton,
        _closeButton,
        _restartButton
      )
    }
  }

  /**
   * This method create a ToggleButton of a type of tower
   *
   * @param imageName path of the image of the tower
   * @param towerName name of the tower
   */
  private def createTowerToggleButton(imageName: String, towerName: String, toggleId: String): ToggleButton = {
    val img = Utils.getImageViewFromResource(imageName)
    img.fitWidth = DefaultConfig.TOWER_BUTTON_WIDTH
    img.fitHeight = DefaultConfig.TOWER_BUTTON_HEIGHT
    new ToggleButton {
      id = toggleId
      text = towerName
      graphic = img
      prefWidth = 100
      prefHeight = 100
      layoutX = DefaultConfig.TOWER_BUTTON_LAYOUT_X
      layoutY = DefaultConfig.TOWER_BUTTON_LAYOUT_Y
      accessibleText = towerName
    }
  }

  private val _baseTowerToggleButton: ToggleButton = {
    val id: String = "baseTower"
    createTowerToggleButton(DefaultConfig.BASE_TOWER_IMAGE,
      DefaultConfig.BASE_TOWER_NAME, id)
  }

  private val _cannonTowerToggleButton: ToggleButton = {
    val id: String = "cannonTower"
    createTowerToggleButton(DefaultConfig.CANNON_TOWER_IMAGE,
      DefaultConfig.CANNON_TOWER_NAME, id)
  }

  private val _flameTowerToggleButton: ToggleButton = {
    val id: String = "flameTower"
    createTowerToggleButton(DefaultConfig.FLAME_TOWER_IMAGE,
      DefaultConfig.FLAME_TOWER_NAME, id)
  }

  private val topRightPane: VBox = {
    new VBox {
      padding = Insets(OPTIONS_PADDING)
      spacing = OPTIONS_SPACING
      alignment = Pos.Center
      children = List(
        _baseTowerToggleButton,
        _cannonTowerToggleButton,
        _flameTowerToggleButton
      )
    }
  }

  private val _healthLabel: Label = {
    new Label {
      text = PLAYER_HEALTH_LABEL
      id = PLAYER_HEALTH_LABEL_ID
    }
  }

  private val _moneyLabel: Label = {
    new Label {
      text = Player_MONEY_LABEL
      id = PLAYER_MONEY_LABEL_ID
    }
  }

  private val _bottomRightPane: VBox = {
    new VBox {
      padding = Insets(OPTIONS_PADDING)
      spacing = OPTIONS_SPACING
      alignment = Pos.Center
      children = List(
        _healthLabel,
        _moneyLabel
      )
    }
  }

  private val _rightPane: VBox = {
    new VBox {
      padding = Insets(OPTIONS_PADDING)
      spacing = OPTIONS_SPACING
      alignment = Pos.Center
      children = List(
        topRightPane,
        _bottomRightPane
      )
    }
  }

  private val _gameScene: Scene = new Scene {
    root = new BorderPane {
      prefWidth = GAME_WINDOW_WIDTH
      prefHeight = GAME_WINDOW_HEIGHT
      center = _canvas
      bottom = _bottomPane
      right = _rightPane
    }
  }

  def canvas(): Canvas = _canvas

  def graphicContext(): GraphicsContext = _canvas.graphicsContext2D

  def gameScene(): Scene = _gameScene

  def buttons(): Array[Button] = Array(_startButton, _closeButton, _restartButton)

  def towerToggleButtons(): List[ToggleButton] = List(_baseTowerToggleButton, _cannonTowerToggleButton, _flameTowerToggleButton)
}

object GameViewModel {

  def apply(): GameViewModel = new GameViewModel()
}