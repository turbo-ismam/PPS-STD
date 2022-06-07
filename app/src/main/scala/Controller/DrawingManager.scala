package Controller

import ViewTMP.GamePanes
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

object DrawingManager {

  def graphicsContext: GraphicsContext = GamePanes.graphicContext

  def drawTile(x: Double, y: Double, c: Color): Unit = {
    graphicsContext.fill = c
    graphicsContext.fillRect(x, y, 64, 64)
  }

  def drawGrid(gameController: GameController): Unit = {
    gameController.getGridController.drawingInfo.foreach(tileTriplet => {
      DrawingManager.drawTile(tileTriplet._2, tileTriplet._3, tileTriplet._1)
    })
  }

  def print(): Unit = {
    println(graphicsContext.toString())
  }

}
