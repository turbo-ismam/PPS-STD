package Controller

import Configuration.DefaultConfig
import Controller.Wave.Wave
import Model.Enemy.Enemy
import Model.Grid.GridController
import Model.Grid.Tiles.TileTypes
import Model.Player

/**
 * A class that manages and schedules every wave.
 */
class WaveScheduler {

  private var _firstWave: Boolean = false

  def firstWave: Boolean = _firstWave

  def firstWave_=(firstWave: Boolean): Unit = {
    _firstWave = firstWave
  }

  def start(wave: Wave): Wave = {
    if (firstWave) {
      wave.nextWave()
    }
    else {
      wave
    }
  }

  def update_check(player: Player, enemy: Enemy, gameController: GameController, gridController: GridController): Unit = {
    gridController.tileStartOrEnd(TileTypes.EndTile) match {
      case Some(tile) =>
        if (!enemy.isAlive) {
          player.addMoney(DefaultConfig.MONEY_EARNED)
          gameController.removeEnemy(enemy)
        }
        if (enemy.enemyCurrentPosition().yPlace == tile.yPlace && enemy.enemyCurrentPosition().xPlace == tile.xPlace) {
          player.updateHealth(enemy.getType.damage, decrement = true)
          enemy.destroy()
          gameController.removeEnemy(enemy)
        }
      case None => throw new Exception("Something went wrong, map hasn't an end tile")
    }
  }

  def check_new_wave(gameController: GameController, wave: Wave): Wave = {
    if (gameController.enemies.isEmpty && !wave.hasEnemies && _firstWave) {
      wave.nextWave()
    }
    else {
      wave
    }
  }
}

object WaveScheduler {
  def apply(): WaveScheduler = {
    val waveScheduler: WaveScheduler = new WaveScheduler
    waveScheduler
  }
}
