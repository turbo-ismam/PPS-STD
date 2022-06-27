package Cache

import Configuration.DefaultConfig.EMPTY_MAP_PATH
import Model.Player

object TowerDefenseCache {

  private var _loadedMap: Option[String] = None
  private var _playerName: Option[String] = None
  private var _difficulty: Option[Int] = None
  private var _gameType: Option[Boolean] = None // t with difficulty , f for custom map

  def loadedMap_=(mapPath: String): Unit = {
    _loadedMap = Some(mapPath)
  }

  def loadedMap: String = {
    _loadedMap match {
      case Some(value) => value
      case None => EMPTY_MAP_PATH
    }
  }

  def playerName_=(playerName: String): Unit = {
    _playerName = Some(playerName)
  }

  def playerName: Option[String] = _playerName

  def difficulty_=(difficulty: Int): Unit = {
    _difficulty = Some(difficulty)
  }

  def difficulty: Option[Int] = _difficulty

  def gameType_=(gameType: Boolean): Unit = {
    _gameType = Some(gameType)
  }

  def gameType: Option[Boolean] = _gameType
}
