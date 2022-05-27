package View

import Controller.DrawingManager
import Controller.DrawingManager.graphicsContext
import Model.Grid.Grid
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.layout.{BorderPane, StackPane}
import scalafx.scene.paint.Color.{Black, Red}

object Launcher extends JFXApp3 {

  val grid = new Grid(1)

  //Create canvas where the field is.
  val gameHeight = 960
  val gameWidth = 1280
  val gameCanvas = new Canvas(gameWidth, gameHeight)
  val gc: GraphicsContext = gameCanvas.graphicsContext2D

  //Animation timer and the time of the game.
  var lastTime = 0L

  val timer: AnimationTimer = AnimationTimer { t =>
    if (lastTime != 0) {
      val delta = (t - lastTime) / 1e9 //In seconds.
      update(delta)
    }
    lastTime = t
  }

  // move this block to controller
  def update(delta: Double): Unit = {
    //Current wave from 0 to X
    if (true) {
      grid.draw()
    }
  }

  override def start(): Unit = {

    stage = new JFXApp3.PrimaryStage {

      title = "TowerDefenseGame"
      height = gameHeight
      width = gameWidth
      scene = new Scene(gameWidth, gameWidth) {

        val fieldStack = new StackPane //Using stack pane here to interact with the game.
        fieldStack.children = List(gameCanvas)

        val rootPane = new BorderPane
        rootPane.center = fieldStack

        gameCanvas.graphicsContext2D.fill = Black
        gameCanvas.graphicsContext2D.fillRect(1, 1, 64, 64)

        DrawingManager.drawTile(2, 2, Black)

        gc.fill = Red
        gc.fillRect(5*64,5*64,64,64)

        DrawingManager.print()

        root = rootPane
      }
    }


  }
}
