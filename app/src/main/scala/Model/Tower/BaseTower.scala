package Model.Tower

import Configuration.DefaultConfig

object BaseTower extends ShooterTower(1) {

  override val name = DefaultConfig.BASE_TOWER_NAME
  override val desc = DefaultConfig.BASE_TOWER_NAME

}
