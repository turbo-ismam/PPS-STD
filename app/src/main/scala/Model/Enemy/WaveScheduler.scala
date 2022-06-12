package Model.Enemy

import Controller.GameController

object WaveScheduler {

  def update_check(enemy: Enemy,gameController: GameController,wave: WaveImpl): WaveImpl = {
    if(!enemy.isAlive()) {
      gameController -= enemy
      if (gameController.enemies.isEmpty) {
        wave.nextWave()
      }
      else {
        wave
      }
    }
    else {
      wave
    }
  }

}
