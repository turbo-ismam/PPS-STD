package Utility

import Utility.Configuration.DefaultConfig
import org.scalatest.funsuite.AnyFunSuite

class UtilsTest extends AnyFunSuite {

  test("Reading image from resources") {
    assert(!Utils.getImageFromResource(DefaultConfig.BASE_TOWER_IMAGE).isError)
    assert(!Utils.getImageFromResource(DefaultConfig.CANNON_TOWER_IMAGE).isError)
    assert(!Utils.getImageFromResource(DefaultConfig.FLAME_TOWER_IMAGE).isError)
  }

}
