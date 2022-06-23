package Controller

import Logger.LogHelper
import View.ViewController.GameViewController
import scalafx.scene.image.Image
import scalafx.scene.paint.Color

object DrawingManager extends LogHelper {

  def drawTile(x: Double, y: Double, c: Color, gameViewController: GameViewController): Unit = {
    val graphicContext = gameViewController.gameViewModel.canvas().graphicsContext2D
    graphicContext.fill = c
    graphicContext.fillRect(x, y, 64, 64)
  }

  def drawGrid(gameController: GameController, gameViewController: GameViewController): Unit = {
    gameController.gridController.drawingInfo.foreach(tileTriplet => {
      DrawingManager.drawTile(tileTriplet._2, tileTriplet._3, tileTriplet._1, gameViewController)
    })
  }

  def drawTower(x: Double, y: Double, image: Image, gameViewController: GameViewController): Unit = {
    val graphicContext = gameViewController.gameViewModel.canvas().graphicsContext2D
    graphicContext.drawImage(image, x, y, 64, 64)
  }

  def drawProjectile(x: Double, y: Double, image: Image, gameViewController: GameViewController): Unit = {
    val graphicContext = gameViewController.gameViewModel.canvas().graphicsContext2D
    graphicContext.drawImage(image, x, y, 64, 64)
  }

  def drawCircle(x: Double, y: Double, r: Int, c: Color, gameViewController: GameViewController): Unit = {
    val graphicContext = gameViewController.gameViewModel.canvas().graphicsContext2D
    graphicContext.fill = c
    graphicContext.globalAlpha = 200.0
    graphicContext.fillOval(x, y, r, r)
  }

  def enemyDraw(x: Double, y: Double, image: Image, gameViewController: GameViewController): Unit = {
    val graphicContext = gameViewController.gameViewModel.canvas().graphicsContext2D
    graphicContext.drawImage(image, x, y, 64, 64)
  }

}
