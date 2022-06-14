package Model.Tower

import Configuration.DefaultConfig
import Model.Tower.TowerTypes.FLAME_TOWER

class FlameTower extends CircularRadiusTower {

  override val name = DefaultConfig.FLAME_TOWER_NAME
  override val desc = DefaultConfig.FLAME_TOWER_DESC
  override val tower_graphic = DefaultConfig.FLAME_TOWER_IMAGE
  override val projectile_graphic = DefaultConfig.FLAME_PROJECTILE_IMAGE
  rangeInTiles = 4
  damage = 1

  override def tower_type: TowerTypes.TowerType = FLAME_TOWER
}
