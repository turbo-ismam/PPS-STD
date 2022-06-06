package View

import Cache.TowerDefenseCache
import Configuration._
import Controller.{DrawingManager, GameController}
import Logger.LogHelper
import Model.Tower.TowerTypes
import View.GameView.gameController
import javafx.event.ActionEvent
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color.Black

object GameView extends LogHelper {

  var primaryStage: PrimaryStage = null
  var gameController: GameController = null

  def hookupEvents(): Unit = {
    val eventsHandler = GamePanes.eventsHandler
    val gameCanvas = GamePanes.getGameCanvas

    // Game Bottom Buttons
    GamePanes.getButtons.foreach(button => {
      button.getText match {
        case "Lets Start!" => button.onAction = (_: ActionEvent) => {
          //Check when selected tower is not defined
          gameCanvas.addEventHandler(MouseEvent.MouseClicked,
            eventsHandler.createTowerEventHandler(TowerDefenseCache.selectedTower.get))
          gameController = new GameController("onlyForTest", 1)
          drawGrid(gameController)
        }
        case "Close!" => button.setOnAction(eventsHandler.unimplementedButtonAlert)
        case "Restart!" => {
          if (primaryStage != null)
            button.setOnAction(eventsHandler.changeScene(primaryStage, mainMenuScene))
          else
            logger.error("Primary Stage is null")
        }
      }
    })

    // Main Menu Buttons
    MainMenuPanes.getButtons.foreach(button => {
      button.getText match {
        case "Start Game!" => button.setOnAction(eventsHandler.changeScene(primaryStage, gameScene))
        case "Exit Game" => button.onAction = (_: ActionEvent) => ???
      }
    })

    //Tower buttons
    GamePanes.getTowerToggleButtons.foreach(toggleButton => {
      toggleButton.getId match {
        case "baseTower" =>
          toggleButton.setOnAction(eventsHandler.selectTower(TowerTypes.BASE_TOWER))
        case "cannonTower" =>
          toggleButton.setOnAction(eventsHandler.selectTower(TowerTypes.CANNON_TOWER))
        case "flameTower" =>
          toggleButton.setOnAction(eventsHandler.selectTower(TowerTypes.FLAME_TOWER))
        case _ =>
          logger.warn("Not implemented yet")
      }
    })

  }

  private def drawGrid(gameController: GameController): Unit = {
    gameController.getGridController.getDrawingInfo.foreach(tileTriplet => {
      DrawingManager.drawTile(tileTriplet._2, tileTriplet._3, Black)
    })
    DrawingManager.drawTile(1*32,1*32,Black)
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
      prefWidth = Configuration.getInt("CanvasWidth", DefaultConfig.CANVAS_WIDTH).toDouble
      prefHeight = Configuration.getInt("CanvasHeight", DefaultConfig.CANVAS_HEIGHT).toDouble
      center = centralPane
    }
  }

}
