package Model.Enemy

import Model.Grid.Tile

trait Enemy {

  def update(delta: Double): Unit

  def getX(): Double

  def getY(): Double

  def getType(): EnemyType //Get enemy type

  def spawn(): Unit //spawn enemy

  def move(delta: Double): Unit //Move all enemies on the next available tile

  def enemyCurrentPosition(): Tile//The tile that the enemy is currently standing.

  def takeDamage(i: Int): Unit //Deal damage to enemy

  def isAlive(): Boolean //Check if enemy is alive

  def death(): Unit //Eliminate enemy

  def destroy(): Unit //Eliminate enemy completely. Can be used when the enemy reaches the end.

}
