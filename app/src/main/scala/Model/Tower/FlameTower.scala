package Model.Tower

import java.io.File
import javax.imageio.ImageIO

//Parameter projectile type. To setup after define projectile implementation
object FlameTower extends ShooterTower(3) {
  override val name = "Flame Tower"
  //To defined, what flame Tower do
  override val desc = "Flame Tower"

  override val tower_graphic = new File(getClass().getResource("/towers/flame_tower.png").getPath())
  override val projectile_graphic = new File(getClass().getResource("/projectiles/flame_projectile.png").getPath())

  override def serialize(): Int = TowerType.FLAME_TOWER
}
