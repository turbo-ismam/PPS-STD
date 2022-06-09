package Cache

import Model.Player

object TowerDefenseCache {

  private var _selectedGameDifficult: Option[String] = None
  private var _player: Option[Player] = None

  def selectedGameDifficult: Option[String] = _selectedGameDifficult

  def player: Option[Player] = _player

  def selectedGameDifficult_=(selectedGameDifficult: Option[String]): Unit = {
    _selectedGameDifficult = selectedGameDifficult
  }

  def player_=(player: Option[Player]): Unit = {
    _player = player
  }
}
