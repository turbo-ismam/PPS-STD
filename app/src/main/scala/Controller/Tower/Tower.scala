package Controller.Tower

import Model.Tower.TowerType

/**
 * Tower superclass from which evey special tower is derived
 * @param tower_type: Type of tower
 * @param x: position of tower in the map
 * @param y: position of tower in the map
 * @param gamestate Maybe can be an observable or something where tower can get game status
 */
class Tower(tower_type: TowerType,
            x: Double,
            y: Double,
            gamestate: GameState)
{
  import Model.Tower._


}
