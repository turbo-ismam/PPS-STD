package Model

import Configuration.DefaultConfig

/**
 * Represent the type of the player
 */
trait Player {

  /**
   * This method retrieve the name of the player
   * @return the player name
   */
  def name: String

  /**
   * This method retrieve the health of the player, if the health go to 0 the player is dead
   *
   * @return the player health
   */
  def health: Int

  /**
   * This method retrieve the money of the player, it decrease when the tower is built and increase when enemy is
   * killed
   * @return the player money
   */
  def money: Int

  /**
   * This method retrieve the total kill of a single game
   * @return the total kill of in a game
   */
  def killCounter: Int

  /**
   * This method is used to update the health of the player
   * @param amount quantity of life with
   * @param decrement true for increment, decrement otherwise
   */
  def updateHealth(amount: Int, decrement: Boolean): Unit

  /**
   * This method is used to increment the money of the player
   * @param amount to add from player money
   */
  def addMoney(amount: Int): Unit

  /**
   * This method is used to decrement the player money
   * @param amount to remove from player money
   * @return true if the amount is less than player total money, false otherwise, in this case the money isn't removed
   */
  def removeMoney(amount: Int): Boolean

  /**
   * This method is used increment the kill counter of the player
   * It is called every time an enemy is killed
   */
  def incrementKillCounter(): Unit
}


object Player {

  private sealed case class PlayerImpl private(_name: String) extends Player{

    var _health: Int = DefaultConfig.INITIAL_HEALTH
    var _money: Int = DefaultConfig.INITIAL_MONEY
    var _killCounter: Int = 0

    override def name: String = _name

    override def health: Int = _health

    override def money: Int = _money

    override def killCounter: Int = _killCounter

    override def updateHealth(amount: Int, decrement: Boolean): Unit = {
      if (decrement)
        _health -= amount
      else
        _health += amount
    }

    override def addMoney(amount: Int): Unit = _money += amount

    override def removeMoney(amount: Int): Boolean = {
      if ((_money - amount) >= 0) {
        _money -= amount
        return true
      }
      false
    }

    override def incrementKillCounter(): Unit = _killCounter += 1
  }

  def apply(name: String): Player = PlayerImpl(name)
}