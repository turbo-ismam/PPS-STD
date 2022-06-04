package Model

import Configuration.DefaultConfig

class Player(name: String) {

  var playerName: String = name
  var health: Int = DefaultConfig.INITIAL_HEALTH
  var money: Int = DefaultConfig.INITIAL_MONEY
  var killCounter: Int = 0

  def getPlayerName: String = playerName

  def getHealth: Int = health

  def updateHealth(amount: Int, decrement: Boolean): Unit = {
    if (decrement)
      health -= amount
    else
      health += amount
  }

  def updateMoney(amount: Int, decrement: Boolean): Unit = {
    if (decrement)
      money -= amount
    else
      money += amount
  }

  def incrementKillCounter(): Unit = killCounter += 1

}

object Player {

  def apply(name: String): Player = new Player(name)
}