package Controller

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color
import View.Launcher

object DrawingManager {

  def graphicsContext: GraphicsContext = Launcher.gc

  def drawTile(x: Double, y: Double, c: Color): Unit = {
    graphicsContext.fill = c
    graphicsContext.fillRect(x, y, 64, 64)
  }

  def print(): Unit = {
    println(graphicsContext.toString())
  }

}
