package View

import Logger.LogHelper
import Configuration.Configuration
import javafx.event.ActionEvent
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{BorderPane, HBox, VBox}

object GameView extends LogHelper{

  var primaryStage: PrimaryStage = null

  def hookupEvents(): Unit = {
    val eventsHandler = GamePanes.eventsHandler
    val gameCanvas = GamePanes.getGameCanvas

    // Game Bottom Buttons
    GamePanes.getButtons.foreach(button => {
      button.getText match {
        case "Lets Start!" => button.onAction = (_: ActionEvent) =>
          gameCanvas.addEventHandler(MouseEvent.MouseClicked, eventsHandler.tileClickEventHandler)
        case "Close!" => button.setOnAction(eventsHandler.unimplementedButtonAlert)
        case "Restart!" => {
          if(primaryStage != null)
            button.setOnAction(eventsHandler.changeScene(primaryStage,mainMenuScene))
          else
            logger.error("Primary Stage is null")
        }
      }
    })

    // Main Menu Buttons
    MainMenuPanes.getButtons.foreach(button => {
      button.getText match {
        case "Start Game!" => button.setOnAction(eventsHandler.changeScene(primaryStage,gameScene))
        case "Exit Game" => button.onAction = (_: ActionEvent) => ???
      }
    })
  }

  def setStage(stage: PrimaryStage): Unit = {
    primaryStage = stage
  }

  val gameScene: Scene = new Scene {
    val gameCanvas: Canvas = GamePanes.getGameCanvas
    val bottomPane: HBox = GamePanes.getBottomPane
    val rightPane: VBox = GamePanes.getRightPane
    root = new BorderPane {
      center = gameCanvas
      bottom = bottomPane
      right = rightPane
    }
  }

  val mainMenuScene: Scene = new Scene {
    val centralPane: VBox = MainMenuPanes.getCentralPane
    root = new BorderPane {
      prefWidth = Configuration.getInt("CanvasWidth", 1280).toDouble
      prefHeight = Configuration.getInt("CanvasHeight", 960).toDouble
      center = centralPane
    }
  }

}
