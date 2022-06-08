package View.Buttons

import Controller.Tower.Tower
import Utility.Utils
import scalafx.scene.control.ToggleButton

class TowerButton(_tower: Option[Tower]) extends ToggleButton {
  if (_tower != None)
    this.graphic = Utils.getImageViewFromResource(_tower.get.image_path())

  var tower = _tower
  val towerName = tower.get.name()
}
