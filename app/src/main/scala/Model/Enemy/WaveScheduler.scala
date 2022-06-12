package Model.Enemy

import Controller.GameController

object WaveScheduler {

  private var _firstWave: Boolean = false

  def firstWave:Boolean = _firstWave

  def firstWave_=(firstWave: Boolean): Unit = {
    _firstWave = firstWave
  }

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
