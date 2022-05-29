package ScalaTowerDefense

import Configuration.ConfigurationReader
import org.scalatest.funsuite.AnyFunSuite

class UtilitiesTest extends AnyFunSuite {

  val c = new ConfigurationReader()

  test("Reading properties") {

    assert(System.getProperty("ProjectName").equals("Tower Defense the GAME"))
    assert(System.getProperty("TestUtilities").equals("442219"))
  }

  test("Writing properties") {

    val tmp = System.getProperty("TestUtilities")
    // setting property
    System.setProperty("TestUtilities", "1110829823")
    // check new property value
    assert(System.getProperty("TestUtilities").equals("1110829823"))
    // setting property to original value
    System.setProperty("TestUtilities", "442219")
    // check if original value is correct
    assert(System.getProperty("TestUtilities").equals("442219"))
  }

  test("Adding properties") {

    assert(System.getProperty("TestProperties2") == null)
    System.setProperty("TestProperties2", "New Property from test!")
    assert(System.getProperty("TestProperties2").equals("New Property from test!"))
  }

}
