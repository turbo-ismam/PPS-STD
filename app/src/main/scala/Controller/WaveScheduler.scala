package Controller

import Controller.Wave.WaveImpl
import Model.Enemy.Enemy
import Model.Grid.Tiles.TileTypes
import Model.Player

class WaveScheduler {

  private var _firstWave: Boolean = false

  def firstWave: Boolean = _firstWave

  def firstWave_=(firstWave: Boolean): Unit = {
    _firstWave = firstWave
  }

  def start(wave: WaveImpl): WaveImpl = {
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
        if (!enemy.isAlive() || (enemy.enemyCurrentPosition().yPlace == tile.yPlace && enemy.enemyCurrentPosition().xPlace == tile.xPlace)) {
          if (enemy.enemyCurrentPosition().yPlace == tile.yPlace && enemy.enemyCurrentPosition().xPlace == tile.xPlace) {
            player.updateHealth(enemy.getType().damage, true)
          }
          gameController.addToRemoveEnemy(enemy)
        }
      case None => throw new Exception("Something went wrong, map hasn't an end tile")
    }
  }

  def check_new_wave(gameController: GameController, wave: WaveImpl): WaveImpl = {
    if (gameController.enemies.isEmpty && !wave.hasEnemies() && _firstWave) {
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
