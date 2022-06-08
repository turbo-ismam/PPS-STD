package ScalaTowerDefense

import Cache.TowerDefenseCache
import Configuration.Configuration
import Model.Tower.TowerTypes.BASE_TOWER
import org.scalatest.funsuite.AnyFunSuite

class UtilitiesTest extends AnyFunSuite {

  //Inizialize configuration
  Configuration()

  test("Reading properties") {

    assert(Configuration.getString("ProjectName", "").equals("Tower Defense the GAME"))
    assert(Configuration.getInt("TestUtilities", 0).equals(442219))
  }

  test("Writing properties") {

    // setting property
    Configuration.setProperty("TestUtilities", "1110829823")
    // check new property value
    assert(Configuration.getInt("TestUtilities", 0).equals(1110829823))
    // setting property to original value
    Configuration.setProperty("TestUtilities", "442219")
    // check if original value is correct
    assert(Configuration.getInt("TestUtilities", 0).equals(442219))
  }

  test("Adding properties") {

    assert(Configuration.getProperty("TestProperties2", "") == "")
    Configuration.setProperty("TestProperties2", "New Property from test!")
    assert(Configuration.getString("TestProperties2", "").equals("New Property from test!"))
  }
  
}
