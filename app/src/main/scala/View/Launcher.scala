package View

import Controller.DrawingManager
import Model.Enemy.{Easy, Enemy, EnemyImpl}
import Controller.Tower.Tower
import Model.Grid.Grid
import Model.Tower.TowerType
import View.EventHandlers.EventsHandler
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.input.DataFormat.Image
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{BorderPane, StackPane}
import scalafx.scene.paint.Color.{Black, Red}

import java.awt.image.BufferedImage

object Launcher extends JFXApp3 {


  val grid = new Grid(1)
  val enemy = new EnemyImpl(Easy, grid)

  //Create canvas where the field is.
  val gameHeight = 1000
  val gameWidth = 1280
  val gameCanvas = new Canvas(gameWidth, gameHeight)
  val gc: GraphicsContext = gameCanvas.graphicsContext2D

  val eventsHandler = new EventsHandler(gc)


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
      //grid.draw()
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

        grid.draw()
        enemy.draw()



        root = rootPane
      }
    }

    gameCanvas.addEventHandler(MouseEvent.MouseClicked, eventsHandler.tileClickEventHandler)
    timer.start()
  }
}
