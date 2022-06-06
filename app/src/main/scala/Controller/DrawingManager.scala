package Controller

import View.GamePanes
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

object DrawingManager {

  def graphicsContext: GraphicsContext = GamePanes.graphicContext

  def drawTile(x: Double, y: Double, c: Color): Unit = {
    graphicsContext.fill = c
    graphicsContext.fillRect(x, y, 64, 64)
  }

  def print(): Unit = {
    println(graphicsContext.toString())
  }

}
