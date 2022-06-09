package ScalaTowerDefense

import Cache.TowerDefenseCache
import Configuration.Configuration
import Model.Tower.TowerTypes.BASE_TOWER
import Utility.Utils.pathFromFile
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source



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

  test("Tower defense cache test"){
    TowerDefenseCache.selectedTower = Some(BASE_TOWER)
    assert(TowerDefenseCache.selectedTower.isDefined)
  }

  test("Check if unsetted property is null"){
    TowerDefenseCache.selectedTower = None
    assert(TowerDefenseCache.selectedTower.isEmpty)
  }

  test("Load path"){
    println("TEST LOAD PATH: ")
    val filePath = getClass.getResource("/DefaultPaths/SimplePath.json").getPath.replace("%20", " ")
    val lines = Source.fromFile(filePath).mkString
    println(lines)
    //lines.foreach(e => println(e))
    /*for(line <- Source.fromFile(filePath).getLines){
      println(line)
    }*/
  }

  test("Json object conversion from file "){

    val filePath = getClass.getResource("/DefaultPaths/SimplePath.json").getPath.replace("%20", " ")

    /*val gson = new Gson();
    val reader = new BufferedReader(new FileReader(filePath))
    val obj = gson.fromJson(reader,classOf[SimplePathJsonObject])
    val lis = obj.map
    lis.foreach(elem => elem.foreach(e => println(e)))*/

    // val map = pathFromFile(filePath).foreach(elem => elem.foreach(e => println(e)))
    val map = pathFromFile(filePath)
    val firstElem = map(0)(0)
    assert(firstElem == 0)
  }


}
