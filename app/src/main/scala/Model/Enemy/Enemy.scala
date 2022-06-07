package Model.Enemy

import Model.Grid.Tile

trait Enemy {

  def update(delta: Double): Unit

  def getType(): EnemyType //Get enemy type

  def spawn(): Unit //spawn enemy

  def move(): Unit //Move all enemies on the next available tile

  def currentTile(): Tile//The tile that the enemy is currently standing.

  def takeDamage(i: Int): Unit //Deal damage to enemy

  def isAlive(): Boolean //Check if enemy is alive

  def death(): Unit //Eliminate enemy

}
