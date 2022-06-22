package Model.Grid

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{CUSTOM_LEVEL, HARD_LEVEL, HARD_PATH_FILE_NAME, NORMAL_LEVEL, NORMAL_PATH_FILE_NAME, SIMPLE_LEVEL, SIMPLE_PATH_FILE_NAME}
import Utility.SimplePathJsonObject
import com.google.gson.Gson

import java.io.{BufferedReader, FileReader}
import java.net.URI

/**
 * This object is used to read the game grid from file system
 */
object PathMaker {

  // Standard size for all grids is 20 x 15

  def simplePath(): Array[Array[Int]] = readMapFromFile(SIMPLE_LEVEL)

  def normalPath(): Array[Array[Int]] =  readMapFromFile(NORMAL_LEVEL)

  def hardPath(): Array[Array[Int]] = readMapFromFile(HARD_LEVEL)

  def customPath(): Array[Array[Int]] = readMapFromFile(CUSTOM_LEVEL)

  def execute(callback:() => Array[Array[Int]]): Array[Array[Int]] = callback()

  private def readMapFromFile(difficulty: Int): Array[Array[Int]] = difficulty match {
      case 1 => pathFromFile(filePathFormatter(SIMPLE_PATH_FILE_NAME))
      case 2 => pathFromFile(filePathFormatter(NORMAL_PATH_FILE_NAME))
      case 3 => pathFromFile(filePathFormatter(HARD_PATH_FILE_NAME))
      case 0 => pathFromFile(TowerDefenseCache.loadedMap)
    }

  private def pathFromFile(fileNamePath: String): Array[Array[Int]] = {
    val gson = new Gson();
    val uri: URI = new URI(fileNamePath)
    val reader = new BufferedReader(new FileReader(uri.getPath))
    gson.fromJson(reader, classOf[SimplePathJsonObject]).map.map(y => y.map(x => x.toInt))
  }

  private def filePathFormatter(path: String): String = getClass.getResource(path).getPath

}
