package Model.Tower

/**
 * An Emum that defines the types of towers available
 */
object TowerTypes extends Enumeration {
  type TowerType = Value
  val BASE_TOWER = Value(1)
  val CANNON_TOWER = Value(2)
  val FLAME_TOWER = Value (3)
}
