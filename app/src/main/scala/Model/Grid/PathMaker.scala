package Model.Grid

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{CUSTOM_LEVEL, HARD_LEVEL, HARD_PATH_FILE_NAME, NORMAL_LEVEL, NORMAL_PATH_FILE_NAME, SIMPLE_LEVEL, SIMPLE_PATH_FILE_NAME}
import Utility.SimplePathJsonObject
import com.google.gson.Gson

import java.io.{BufferedReader, FileReader}
import java.net.URI

/**
 * This class is used to read the game grid from file system
 */
trait PathMaker {

  /**
   * Method to generate the simple path
   * @return an array of array of int, read from the resources of the project
   */
  def simplePath(): Array[Array[Int]]

  /**
   * Method to generate the medium path
   * @return an array of array of int, read from the resources of the project
   */
  def normalPath(): Array[Array[Int]]

  /**
   * Method to generate the hard path
   * @return an array of array of int, read from the resources of the project
   */
  def hardPath(): Array[Array[Int]]

  /**
   *  Method used if the grid is custom, it generate the custom path
   * @return an array of array of int, read from specified folder in the user file system
   */
  def customPath(): Array[Array[Int]]

  /**
   * Method used to generate all paths
   * @param callback method that generate the path
   * @return the effective array of array of int that represent the requested path
   */
  def execute(callback: () => Array[Array[Int]]): Array[Array[Int]] = callback()
}

object PathMaker {

  private case class PathMakerImpl() extends PathMaker {

    // Standard size for all grids is 20 x 15

    def simplePath(): Array[Array[Int]] = readMapFromFile(SIMPLE_LEVEL)

    def normalPath(): Array[Array[Int]] = readMapFromFile(NORMAL_LEVEL)

    def hardPath(): Array[Array[Int]] = readMapFromFile(HARD_LEVEL)

    def customPath(): Array[Array[Int]] = readMapFromFile(CUSTOM_LEVEL)

    override def execute(callback: () => Array[Array[Int]]): Array[Array[Int]] = callback()

    private def readMapFromFile(difficulty: Int): Array[Array[Int]] = difficulty match {
      case 1 => pathFromFile(filePathFormatter(SIMPLE_PATH_FILE_NAME))
      case 2 => pathFromFile(filePathFormatter(NORMAL_PATH_FILE_NAME))
      case 3 => pathFromFile(filePathFormatter(HARD_PATH_FILE_NAME))
      case 0 => pathFromFile(TowerDefenseCache.loadedMap)
    }

    private def pathFromFile(fileNamePath: String): Array[Array[Int]] = {
      new Gson().fromJson(
        new BufferedReader(new FileReader(new URI(fileNamePath).getPath)),
        classOf[SimplePathJsonObject])
        .map.map(y => y.map(x => x.toInt))
    }

    private def filePathFormatter(path: String): String = getClass.getResource(path).getPath

  }

  def apply(): PathMaker = PathMakerImpl()

}
