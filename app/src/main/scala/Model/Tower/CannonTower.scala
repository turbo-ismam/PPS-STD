package Model.Tower

import Model.Tower.FlameTower.getClass

import java.io.File
import javax.imageio.ImageIO

//Parameter projectile type. To setup after define projectile implementation
object CannonTower extends ShooterTower(2) {
  override val name = "Cannon Tower"
  //To defined, what cannon Tower do
  override val desc = "Cannon Tower"

  override val tower_graphic = new File(getClass().getResource("/towers/cannon_tower.png").getPath().replace("%20", " "))
  override val projectile_graphic = new File(getClass().getResource("/projectiles/cannon_projectile.png").getPath().replace("%20", " "))

  override def serialize(): Int = TowerType.CANNON_TOWER
}
