package Controller

import Logger.LogHelper
import scalafx.scene.image.Image
import scalafx.scene.paint.Color

object DrawingManager extends LogHelper {

  def drawTile(x: Double, y: Double, c: Color, gameController: GameController): Unit = {
    val graphicContext = gameController.gameViewController.gameViewModel.canvas().graphicsContext2D
    graphicContext.fill = c
    graphicContext.fillRect(x, y, 64, 64)
  }

  def drawGrid(gameController: GameController): Unit = {
    gameController.getGridController.drawingInfo.foreach(tileTriplet => {
      DrawingManager.drawTile(tileTriplet._2, tileTriplet._3, tileTriplet._1, gameController)
    })
  }

  def drawTower(x: Double, y: Double, image: Image): Unit = {
    //gameGraphicContext.drawImage(image, x, y, 64, 64)
  }

  def drawProjectile(x: Double, y: Double, image: Image): Unit = {
    //gameGraphicContext.drawImage(image, x, y, 64, 64)
  }

  def drawCircle(x: Double, y: Double, r: Int, c: Color): Unit = {
    /*gameGraphicContext.fill = c
    gameGraphicContext.globalAlpha = 200.0
    gameGraphicContext.fillOval(x, y, r, r)*/
  }

  def print(): Unit = {
    //println(gameGraphicContext.toString())
  }

  def enemyDraw(x: Double, y: Double, image: Image): Unit = {
    //
  }

}
