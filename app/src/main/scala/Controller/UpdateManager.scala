package Controller

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Enemy.WaveScheduler
import Model.Tower.{CircularRadiusTower, ShooterTower}
import View.ViewController.GameViewController
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color

class UpdateManager(gameController: GameController, gameViewController: GameViewController) extends LogHelper {

  private var alive: Boolean = true

  private def update(delta: Double): Unit = {
    if (alive) {
      DrawingManager.drawGrid(gameController, gameViewController)

      gameController.towers.foreach(tower => {
        updateTower(delta, tower)
      })

      gameController.enemies.foreach(enemy => {
        enemy.update(delta)
        val x = enemy.getX()
        val y = enemy.getY()
        DrawingManager.enemyDraw(x, y, enemy.getType().image, gameViewController)
        WaveScheduler.update_check(gameController.player, enemy, gameController, gameController.getGridController)
      })

      gameController.enemies --= gameController.toRemoveEnemies

      gameController.wave = WaveScheduler.check_new_wave(gameController, gameController.wave)

      gameController.wave.update(delta)
      if (gameController.player.health <= 0) {
        alive = false
        logger.info("Player {} lose the game ", gameController.player.playerName)
        logger.info("Player {} stats : \n kill counter: {} ", gameController.player.playerName, gameController.player.killCounter)
        return
      }
    }
  }

  private def updateTower(delta: Double, tower: Tower): Unit = {
    if (tower.towerType.isInstanceOf[ShooterTower]) {
      updateShooterTower(delta, tower)
    }
    if (tower.towerType.isInstanceOf[CircularRadiusTower]) {
      updateCircularRadiusTower(delta, tower)
    }
  }

  private def updateShooterTower(delta: Double, tower: Tower): Unit = {
    if (!tower.towerType.targeted) tower.towerType.choose_target()
    tower.timeSinceLastShot += delta
    if (tower.timeSinceLastShot > tower.firingSpeed && !gameController.enemies.isEmpty) tower.tower_type().attack()
    tower.tower_type().choose_target()
    tower.projectiles.foreach(projectile => {
      projectile.update(delta)
      if (projectile.alive) {
        val x = projectile.pos.x
        val y = projectile.pos.y
        //Draw projectile
        DrawingManager.drawCircle(x, y, projectile.projectileDiameter, Color.Black, gameViewController)
      }
    })
    //Avoid ConcurrentModificationException
    //I can't do it inside foreach
    // more info here: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ConcurrentModificationException.html
    tower.projectiles --= tower.toRemoveProjectiles
    DrawingManager.drawTower(tower.posX, tower.posY, tower.graphic(), gameViewController)
  }

  private def updateCircularRadiusTower(delta: Double, tower: Tower): Unit = {
    tower.timeSinceLastShot += delta
    if (tower.timeSinceLastShot < 0.5) {
      tower.displayShotInRange = true
      DrawingManager.drawTower(tower.posX, tower.posY, tower.graphic(), gameViewController)
      displayShotInRange(tower.towerType.circularRadiusTowerShootColor, tower)
    } else {
      tower.displayShotInRange = false
      DrawingManager.drawTower(tower.posX, tower.posY, tower.graphic(), gameViewController)
    }
    if (tower.timeSinceLastShot > tower.firingSpeed) {
      tower.displayShotInRange = true
      displayShotInRange(tower.towerType.circularRadiusTowerShootColor, tower)
      tower.towerType.attack()
    }
  }

  private def displayShotInRange(color: Color, tower: Tower): Unit = {
    if (tower.towerType.isInstanceOf[CircularRadiusTower]) {
      tower.displayShotInRange = true
      DrawingManager.drawCircle(tower.circleRadiusX, tower.circleRadiusY, tower.rangeInTiles * tower.cellSize, color, gameViewController)
    }
  }

  def run(): AnimationTimer = {
    logger.info("Start tower defense game")

    //Animation timer and the time of the game.
    var lastTime = 0L

    val timer = AnimationTimer { t =>
      if (lastTime != 0) {
        //1e9 convert nanoseconds to seconds
        val delta = (t - lastTime) / 1e9
        update(delta)
      }
      lastTime = t
    }
    timer
  }
}

object UpdateManager {
  def apply(gameController: GameController, gameViewController: GameViewController): UpdateManager = {
    val updateManager: UpdateManager = new UpdateManager(gameController, gameViewController)
    updateManager
  }
}
