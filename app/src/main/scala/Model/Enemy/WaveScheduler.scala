package Model.Enemy

import Controller.{GameController, GridController}
import Model.Grid.Tiles.TileTypes

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

  def update_check(enemy: Enemy,gameController: GameController,wave: WaveImpl, gridController: GridController): WaveImpl = {

    gridController.tileWithFilter(TileTypes.EndTile) match {
      case Some(tile) =>
        if(!enemy.isAlive() || enemy.enemyCurrentPosition() == tile) {
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
      case None => throw new Exception("Something went wrong, map hasn't an end tile")
    }
  }

}
