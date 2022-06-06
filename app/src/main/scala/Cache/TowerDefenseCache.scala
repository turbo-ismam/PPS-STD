package Cache

import Model.Tower.TowerTypes.TowerType

object TowerDefenseCache {

  private var _selectedTower: Option[TowerType] = None

  def selectedTower: Option[TowerType] = _selectedTower

  def selectedTower_=(selectedTower: Option[TowerType]): Unit = {
    _selectedTower = selectedTower
  }
}
