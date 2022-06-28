package Controller

import Controller.Tower.Tower
import Model.Tower.{CircularRadiusTower, ShooterTower}
import Utility.Configuration.DefaultConfig
import Utility.Configuration.DefaultConfig.{PLAYER_HEALTH_LABEL_ID, PLAYER_MONEY_LABEL_ID}
import Utility.Logger.LogHelper
import View.ViewController.GameViewController
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color

class UpdateManager(gameController: GameController, gameViewController: GameViewController) extends LogHelper {

  private var alive: Boolean = true
  private val cellSize = DefaultConfig.CELL_SIZE

  private def update(delta: Double): Unit = {
    if (alive) {
      DrawingManager.drawGrid(gameController, gameViewController)

      gameController.towers.foreach(tower => {
        updateTower(delta, tower)
      })

      gameController.enemies.foreach(enemy => {
        enemy.update(delta)
        val x = enemy.getX
        val y = enemy.getY
        DrawingManager.enemyDraw(x, y, enemy.getType.image, gameViewController)
        gameController.waveScheduler.update_check(gameController.player, enemy, gameController, gameController.gridController)
      })

      gameController.enemies --= gameController.junkEnemies

      gameController.wave = gameController.waveScheduler.check_new_wave(gameController, gameController.wave)

      gameController.wave.update()
      if (gameController.player.health <= 0) {
        alive = false
        logger.info("Player {} lose the game ", gameController.player.name)
        logger.info("Player {} stats : \n kill counter: {} ", gameController.player.name, gameController.player.killCounter)
        return
      }

      gameViewController.gameViewModel.labels.foreach(label => label.getId match {
        case PLAYER_HEALTH_LABEL_ID => label.setText("Health: " + gameController.player.health.toString)
        case PLAYER_MONEY_LABEL_ID => label.setText("Money: " + gameController.player.money.toString)
      })
    }
  }

  private def updateTower(delta: Double, tower: Tower): Unit = {
    tower.towerType match {
      case _: ShooterTower =>
        updateShooterTower(delta, tower)
      case _: CircularRadiusTower =>
        updateCircularRadiusTower(delta, tower)
    }
  }

  private def updateShooterTower(delta: Double, tower: Tower): Unit = {
    if (!tower.towerType.targeted) tower.towerType.chooseTarget()
    tower.timeSinceLastShot += delta
    if (tower.timeSinceLastShot > tower.firingSpeed && !gameController.enemies.isEmpty) tower.towerType.attack()
    tower.towerType.chooseTarget()
    tower.projectiles.foreach(projectile => {
      projectile.update(delta)
      if (projectile.alive) {
        val x = projectile.pos.x
        val y = projectile.pos.y
        DrawingManager.drawCircle(x, y, projectile.projectileDiameter, projectile.projectileColor, gameViewController)
      }
    })
    //Avoid ConcurrentModificationException
    tower.projectiles --= tower.junkProjectiles
    DrawingManager.drawTower(tower.towerPosition.x, tower.towerPosition.y, tower.graphic(), gameViewController)
  }

  private def updateCircularRadiusTower(delta: Double, tower: Tower): Unit = {
    tower.timeSinceLastShot += delta
    if (tower.timeSinceLastShot < 0.5) {
      tower.displayShotInRange = true
      DrawingManager.drawTower(tower.towerPosition.x, tower.towerPosition.y, tower.graphic(), gameViewController)
      displayShotInRange(tower.towerType.circularRadiusTowerShootColor, tower)
    } else {
      tower.displayShotInRange = false
      DrawingManager.drawTower(tower.towerPosition.x, tower.towerPosition.y, tower.graphic(), gameViewController)
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
      DrawingManager.drawCircle(tower.circularRadius.x, tower.circularRadius.y, tower.rangeInTiles * cellSize, color, gameViewController)
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
