package Controller

import View.ViewController.GameViewController
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

object DrawingManager {

  def gameGraphicContext: GraphicsContext = GameViewController.game_view_model.get.graphicContext()

  def drawTile(x: Double, y: Double, c: Color): Unit = {
    gameGraphicContext.fill = c
    gameGraphicContext.fillRect(x, y, 64, 64)
  }

  def drawGrid(gameController: GameController): Unit = {
    gameController.getGridController.getDrawingInfo.foreach(tileTriplet => {
      DrawingManager.drawTile(tileTriplet._2, tileTriplet._3, tileTriplet._1)
    })
  }

  def print(): Unit = {
    println(gameGraphicContext.toString())
  }

  def enemyDraw(x:Double,y:Double,color: Color): Unit = {
    drawTile(x, y, color)
  }

}
