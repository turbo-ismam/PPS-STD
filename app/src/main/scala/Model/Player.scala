package Model

import Configuration.DefaultConfig

trait Player {

  def name: String

  def health: Int

  def money: Int

  def killCounter: Int

  def updateHealth(amount: Int, decrement: Boolean): Unit

  def addMoney(amount: Int): Unit

  def removeMoney(amount: Int): Boolean

  def incrementKillCounter(): Unit
}


object Player {

  private sealed case class PlayerImpl private(_name: String) extends Player{

    var _health: Int = DefaultConfig.INITIAL_HEALTH
    var _money: Int = DefaultConfig.INITIAL_MONEY
    var _killCounter: Int = 0

    def name: String = _name

    def health: Int = _health

    def money: Int = _money

    def killCounter: Int = _killCounter

    def updateHealth(amount: Int, decrement: Boolean): Unit = {
      if (decrement)
        _health -= amount
      else
        _health += amount
    }

    def addMoney(amount: Int): Unit = _money += amount

    def removeMoney(amount: Int): Boolean = {
      if ((_money - amount) >= 0) {
        _money -= amount
        return true
      }
      false
    }

    def incrementKillCounter(): Unit = _killCounter += 1
  }

  def apply(name: String): Player = PlayerImpl(name)
}