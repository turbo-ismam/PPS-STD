package Model.Enemy
import Controller.GridController
import Model.Grid.{Grid, Tile}

trait Wave {

  def update(delta: Double): Unit

  def getWave(): Int //Get wave number.

  def populate(i: Int, e: EnemyType, grid: GridController): Unit //Method to insert enemies in a wave.

  def spawn(): Unit //Method to spawn enemies.

  def nextWave() : Wave //Get next wave.

  def hasEnemies(): Boolean //Check if wave has enemies

  def clearWave(): Unit //Clear current wave of enemies

}
