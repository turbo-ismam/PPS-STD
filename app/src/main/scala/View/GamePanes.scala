package View

import Configuration.Configuration
import Utility.Utils
import View.EventHandlers.EventsHandler
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.{Button, Label, ToggleButton}
import scalafx.scene.layout.{HBox, VBox}

object GamePanes {

  var graphicContext: GraphicsContext = null
  var eventsHandler: EventsHandler = null

  private val gameCanvas: Canvas = {
    val canvasWidth = Configuration.getInt("CanvasWidth", 1280)
    val canvasHeight = Configuration.getInt("CanvasHeight", 960)
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
   * @param imageName path of the image of the tower
   * @param towerName name of the tower
   */
  private def createTowerToggleButton(imageName: String, towerName: String): ToggleButton = {
    val img = Utils.uploadImage(imageName)
    img.fitWidth = 150
    img.fitHeight = 150
    new ToggleButton {
      text = towerName
      graphic = img
      layoutX = 0
      layoutY = 50
      accessibleText = towerName
    }
  }

  private val baseTower: ToggleButton = {
    createTowerToggleButton(Configuration.getString("BaseTowerUrl", "/towers/base_tower.png"),
      Configuration.getString("BaseTowerName","Base Tower"))
  }

  private val cannonTower: ToggleButton = {
    createTowerToggleButton(Configuration.getString("CannonTowerUrl","/towers/cannon_tower.png"),
      Configuration.getString("CannonTowerName","Cannon Tower"))
  }

  private val flameTower: ToggleButton = {
    createTowerToggleButton(Configuration.getString("FlameTowerUrl","/towers/flame_tower.png"),
      Configuration.getString("FlameTowerName", "Flame Tower"))
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

  def getTowerToggleButtons: Array[ToggleButton] = Array(baseTower, cannonTower,baseTower)


}
