package Cache

import Controller.GameController
import Model.Player
import Model.Tower.TowerTypes.TowerType

object TowerDefenseCache {

  private var _selectedGameDifficult: Option[String] = None
  private var _player: Option[Player] = None
  private var _gameController: Option[GameController] = None


  def selectedGameDifficult: Option[String] = _selectedGameDifficult

  def player: Option[Player] = _player

  def gameController: Option[GameController] = _gameController

  def selectedGameDifficult_=(selectedGameDifficult: Option[String]): Unit = {
    _selectedGameDifficult = selectedGameDifficult
  }

  def player_=(player: Option[Player]): Unit = {
    _player = player
  }

  def gameController_=(gameController: Option[GameController]): Unit = {
    _gameController = gameController
  }
}
