package Model.Tower

//Parameter projectile type. To setup after define projectile implementation
object BaseTower extends ShooterTower (1) {
  override val name = "Basic tower"
  //To defined, what base tower do
  override val desc = "Base tower"
  override def serialize() : Int = TowerType.BASE_TOWER
}
