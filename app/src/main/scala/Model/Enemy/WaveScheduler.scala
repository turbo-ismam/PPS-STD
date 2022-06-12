package Model.Enemy

import Controller.GameController

object WaveScheduler {

  var _firstWave: Boolean = true

  def firstWave:Boolean = _firstWave

  def start(wave: WaveImpl): WaveImpl = {
    if(firstWave){
      _firstWave = false
      wave.nextWave()
    }
    else{
      wave
    }
  }

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
