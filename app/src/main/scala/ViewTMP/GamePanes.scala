package ViewTMP

import Configuration.DefaultConfig
import Logger.LogHelper
import Utility.Utils
import ViewTMP.EventHandlers.EventsHandler
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.{Button, Label, ToggleButton}
import scalafx.scene.layout.{HBox, VBox}

object GamePanes extends LogHelper {

  var graphicContext: GraphicsContext = null
  var eventsHandler: EventsHandler = null
  //Initialize game status and towers

  private val gameCanvas: Canvas = {
    val canvasWidth = 1280
    val canvasHeight = 960
    val gameCanvas = new Canvas(canvasWidth, canvasHeight)
    graphicContext = gameCanvas.graphicsContext2D
    eventsHandler = new EventsHandler(graphicContext)

    gameCanvas
  }


  private val startButton: Button = {
    new Button("Lets Start!")
  }

  private val closeButton: Button = {
    new Button("Close!")
  }

  private val restartButton: Button = {
    new Button("Restart!")
  }

  private val bottomPane: HBox = {
    new HBox {
      padding = Insets(10)
      spacing = 10
      alignment = Pos.Center
      children = List(
        startButton,
        closeButton,
        restartButton
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
      layoutX = DefaultConfig.TOWER_BUTTON_LAYOUT_X
      layoutY = DefaultConfig.TOWER_BUTTON_LAYOUT_Y
      accessibleText = towerName
    }
  }

  private val baseTower: ToggleButton = {
    val id: String = "baseTower"
    createTowerToggleButton(DefaultConfig.BASE_TOWER_IMAGE,
      DefaultConfig.BASE_TOWER_NAME, id)
  }

  private val cannonTower: ToggleButton = {
    val id: String = "cannonTower"
    createTowerToggleButton(DefaultConfig.CANNON_TOWER_IMAGE,
      DefaultConfig.CANNON_TOWER_NAME, id)
  }

  private val flameTower: ToggleButton = {
    val id: String = "flameTower"
    createTowerToggleButton(DefaultConfig.FLAME_TOWER_IMAGE,
      DefaultConfig.FLAME_TOWER_NAME, id)
  }

  private val topRightPane: VBox = {
    new VBox {
      padding = Insets(10)
      spacing = 10
      alignment = Pos.Center
      children = List(
        baseTower,
        cannonTower,
        flameTower
      )
    }
  }

  private val healthLabel: Label = {
    new Label {
      text = "Player Health"
    }
  }

  private val moneyLabel: Label = {
    new Label {
      text = "Player Money"
    }
  }

  private val bottomRightPane: VBox = {
    new VBox {
      padding = Insets(10)
      spacing = 10
      alignment = Pos.Center
      children = List(
        healthLabel,
        moneyLabel
      )
    }
  }

  private val rightPane: VBox = {
    new VBox {
      padding = Insets(10)
      spacing = 10
      alignment = Pos.Center
      children = List(
        topRightPane,
        bottomRightPane
      )
    }
  }

  def getGameCanvas: Canvas = gameCanvas

  def getBottomPane: HBox = bottomPane

  def getButtons: Array[Button] = Array(startButton, closeButton, restartButton)

  def getRightPane: VBox = rightPane

  def getTowerToggleButtons: Array[ToggleButton] = Array(baseTower, cannonTower, flameTower)

}
