package Cache

import Model.Tower.TowerTypes.TowerType

object TowerDefenseCache {

  private var selectedTower: TowerType = _

  def setSelectedTower(selectedTower: TowerType): Unit = {
    this.selectedTower = selectedTower
  }

  def getSelectedTower: TowerType = {
    selectedTower
  }
}
