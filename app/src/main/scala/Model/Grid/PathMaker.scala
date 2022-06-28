package Model.Grid

import Utility.Cache.TowerDefenseCache
import Utility.Configuration.DefaultConfig._
import Utility.SimplePathJsonObject
import com.google.gson.Gson

import java.io.{BufferedReader, File, InputStreamReader}
import java.net.URL

/**
 * This class is used to read the game grid from file system
 */
trait PathMaker {

  /**
   * Method to generate the simple path
   *
   * @return an array of array of int, read from the resources of the project
   */
  def simplePath(): Array[Array[Int]]

  /**
   * Method to generate the medium path
   *
   * @return an array of array of int, read from the resources of the project
   */
  def normalPath(): Array[Array[Int]]

  /**
   * Method to generate the hard path
   *
   * @return an array of array of int, read from the resources of the project
   */
  def hardPath(): Array[Array[Int]]

  /**
   * Method used if the grid is custom, it generate the custom path
   *
   * @return an array of array of int, read from specified folder in the user file system
   */
  def customPath(): Array[Array[Int]]

  /**
   * Method used to generate all paths
   * It check if the generated map is correct, the map is correct if it got exact one tile that as Start and one as End
   *
   * @param callback method that generate the path
   * @return the effective array of array of int that represent the requested path
   */
  def execute(callback: () => Array[Array[Int]]): Array[Array[Int]] = callback()
}

object PathMaker {

  private case class PathMakerImpl() extends PathMaker {

    // Standard size for all grids is 20 x 15

    override def simplePath(): Array[Array[Int]] = readMapFromFile(SIMPLE_LEVEL)

    override def normalPath(): Array[Array[Int]] = readMapFromFile(NORMAL_LEVEL)

    override def hardPath(): Array[Array[Int]] = readMapFromFile(HARD_LEVEL)

    override def customPath(): Array[Array[Int]] = readMapFromFile(CUSTOM_LEVEL)

    override def execute(callback: () => Array[Array[Int]]): Array[Array[Int]] = {
      if (!pathValidator(callback())) System.exit(1)
      callback()
    }

    private def readMapFromFile(difficulty: Int): Array[Array[Int]] = difficulty match {
      case 1 => pathFromFile(filePathFormatter(SIMPLE_PATH_FILE_NAME))
      case 2 => pathFromFile(filePathFormatter(NORMAL_PATH_FILE_NAME))
      case 3 => pathFromFile(filePathFormatter(HARD_PATH_FILE_NAME))
      case 0 => pathFromFile(new File(TowerDefenseCache.loadedMap).toURI.toURL)
    }

    private def pathFromFile(fileNamePath: URL): Array[Array[Int]] = {
      new Gson().fromJson(
        new BufferedReader(new InputStreamReader(fileNamePath.openStream())),
        classOf[SimplePathJsonObject])
        .map.map(y => y.map(x => x.toInt))
    }

    private def pathValidator(path: Array[Array[Int]]): Boolean = {
      if (containSingleStartOrEnd(path, TILE_START_POSITION_ID) &&
        containSingleStartOrEnd(path, TILE_END_POSITION_ID)) true else false
    }

    private def containSingleStartOrEnd(path: Array[Array[Int]], position: Int): Boolean = {
      path.flatMap(_.toSeq).groupBy(identity).view.mapValues(_.length)(position) match {
        case 1 => true
        case _ => false
      }
    }

    private def filePathFormatter(path: String): URL = getClass.getClassLoader()getResource(path)

  }

  def apply(): PathMaker = PathMakerImpl()

}
