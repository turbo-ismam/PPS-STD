package Model.Tower

import Controller.GameController
import Controller.Tower.Tower
import Model.Enemy.Enemy

class CircularRadiusTower extends TowerType {

  private var tower: Option[Tower] = None
  private var gameController: Option[GameController] = None
  val cellSize = 64

  def isColliding(x: Double, y: Double, e: Enemy): Boolean = {
    val enemyPosX = e.enemyCurrentPosition().x
    val enemyPosY = e.enemyCurrentPosition().y
    (x + rangeInTiles * 64 > enemyPosX) && (x < enemyPosX + cellSize) &&
      (y + rangeInTiles * 64 > enemyPosY) && (y < enemyPosY + cellSize)
  }

  override def attack(): Unit = {
    tower.get.timeSinceLastShot = 0
    tower.get.displayShotInRange = true
    val circleRadiusX = tower.get.posX - ((rangeInTiles - 1) * 32)
    val circleRadiusY = tower.get.posY - ((rangeInTiles - 1) * 32)
    tower.get.displayShotInRange(circularRadiusTowerShootColor)
    gameController.get.enemies.foreach(e => {
      if (isColliding(circleRadiusX, circleRadiusY, e)) {
        e.takeDamage(damage)
      }
    })
  }

  override def apply(tower: Tower, gameController: GameController): Unit = {
    this.tower = Option(tower)
    this.gameController = Option(gameController)
  }
}
