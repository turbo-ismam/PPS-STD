package Configuration

object DefaultConfig {
  val PROJECT_NAME = Configuration.getString("ProjectName", "Tower Defense the GAME")

  //Default canvas settings
  val CANVAS_WIDTH = Configuration.getInt("CanvasWidth", 1280)
  val CANVAS_HEIGHT = Configuration.getInt("CanvasHeight", 960)

  //Default towers image
  val BASE_TOWER_IMAGE = Configuration.getString("BaseTowerImage", "/towers/base_tower.png")
  val CANNON_TOWER_IMAGE = Configuration.getString("CannonTowerImage", "/towers/cannon_tower.png")
  val FLAME_TOWER_IMAGE = Configuration.getString("FlameTowerImage", "/towers/flame_tower.png")

  //Default towers name and description
  val BASE_TOWER_NAME = Configuration.getString("BaseTowerName", "Base Tower")
  val CANNON_TOWER_NAME = Configuration.getString("CannonTowerName", "Cannon Tower")
  val FLAME_TOWER_NAME = Configuration.getString("FlameTowerName", "Flame Tower")
  val BASE_TOWER_DESC = Configuration.getString("BaseTowerDesc", "Base Tower")
  val CANNON_TOWER_DESC = Configuration.getString("CannonTowerDesc", "Cannon Tower")
  val FLAME_TOWER_DESC = Configuration.getString("FlameTowerDesc", "Flame Tower")

  //Default projectile image
  val BASE_PROJECTILE_IMAGE = Configuration.getString("BaseProjectileImage", "/projectiles/base_projectile.png")
  val CANNON_PROJECTILE_IMAGE = Configuration.getString("CannonProjectileImage", "/projectiles/cannon_projectile.png")
  val FLAME_PROJECTILE_IMAGE = Configuration.getString("FlameProjectileImage", "/projectiles/flame_projectile.png")

  //Tower button settings
  val TOWER_BUTTON_WIDTH = 150
  val TOWER_BUTTON_HEIGHT = 150
  val TOWER_BUTTON_LAYOUT_X = 0
  val TOWER_BUTTON_LAYOUT_Y = 50

  //Tower base settings
  val TOWER_DAMAGE = 5
  val TOWER_RANGE = 5
  val TOWER_FIRING_SPEED = 4
  val TOWER_PRICE = 50
}
