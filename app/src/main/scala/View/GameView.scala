package View

import View.EventHandlers.EventsHandler
import javafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{BorderPane, HBox, StackPane}

object GameView {

  /*var graphicContext: Option[GraphicsContext] = None
  val eventsHandler:  Option[EventsHandler] = None*/ // TODO check for Some and None

  var graphicContext: GraphicsContext = null
  var eventsHandler: EventsHandler = null

  private val gameCanvas: Canvas = {
    val gameCanvas = new Canvas(System.getProperty("CanvasWidth").toInt,
      System.getProperty("CanvasHeight").toInt)
    graphicContext = gameCanvas.graphicsContext2D
    eventsHandler = new EventsHandler(graphicContext)
    gameCanvas
  }

  private val fieldStackPane: StackPane = {
    val fieldStack = new StackPane
    fieldStack.children = List(gameCanvas)
    fieldStack
  }

  private val startButton: Button = {
    new Button("Lets Start!")
  }

  private val buttonPane: HBox = {
    new HBox {
      padding = Insets(10)
      spacing = 10
      alignment = Pos.Center
      children = List(
        startButton
      )
    }
  }

  def hookupEvents(): Unit = {
    startButton.onAction = (event: ActionEvent) => {
      new Alert(AlertType.Information, "Hello Dialogs!!!").showAndWait()
      gameCanvas.addEventHandler(MouseEvent.MouseClicked, eventsHandler.tileClickEventHandler)
    }
  }

  val scene: Scene = new Scene {
    root = new BorderPane {
      center = fieldStackPane
      bottom = buttonPane
    }
  }
}
