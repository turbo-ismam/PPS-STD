package Controller.Wave

import Model.Enemy.Enemy
import Model.Grid.GridController

import scala.collection.mutable.ListBuffer

trait Wave {

  def update(delta: Double): Unit

  def getWave(): Int //Get wave number.

  def populate(waveNumber: Int, grid: GridController): ListBuffer[Enemy] //Method to insert enemies in a wave.

  def spawn(): Unit //Method to spawn enemies.

  def nextWave(): WaveImpl //Get next wave.

  def hasEnemies(): Boolean //Check if wave has enemies

  def clearWave(): Unit //Clear current wave of enemies

}
