package Model.Tower

import Configuration.DefaultConfig
import Model.Tower.TowerTypes.FLAME_TOWER

class FlameTower extends CircularRadiusTower {

  override val name = DefaultConfig.FLAME_TOWER_NAME
  override val desc = DefaultConfig.FLAME_TOWER_DESC
  override val tower_graphic = DefaultConfig.FLAME_TOWER_IMAGE

  override val firingSpeed: Int = DefaultConfig.FLAME_TOWER_FIRING_SPEED
  override val price: Int = DefaultConfig.FLAME_TOWER_PRICE
  override val damage: Int = DefaultConfig.FLAME_TOWER_DAMAGE
  override val rangeInTiles: Int = DefaultConfig.FLAME_TOWER_RANGE

  override def tower_type: TowerTypes.TowerType = FLAME_TOWER
}

object FlameTower {
  def apply(): FlameTower = {
    val flameTower: FlameTower = new FlameTower()
    flameTower
  }
}