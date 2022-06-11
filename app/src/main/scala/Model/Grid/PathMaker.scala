package Model.Grid

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{CUSTOM_LEVEL, HARD_LEVEL, HARD_PATH_FILE_NAME, NORMAL_LEVEL, NORMAL_PATH_FILE_NAME, SIMPLE_LEVEL, SIMPLE_PATH_FILE_NAME}

object PathMaker {

  // Standard size for all grids is 20 x 15

  def simplePath(): Array[Array[Int]] = readMapFromFile(SIMPLE_LEVEL)

  def normalPath(): Array[Array[Int]] =  readMapFromFile(NORMAL_LEVEL)

  def hardPath(): Array[Array[Int]] = readMapFromFile(HARD_LEVEL)

  def customPath(): Array[Array[Int]] = readMapFromFile(CUSTOM_LEVEL)

  def execute(callback:() => Array[Array[Int]]): Array[Array[Int]] = callback()

  private def readMapFromFile(difficulty: Int): Array[Array[Int]] ={
    difficulty match {
      case 1 => Utility.Utils.pathFromFile(filePathFormatter(SIMPLE_PATH_FILE_NAME))
      case 2 => Utility.Utils.pathFromFile(filePathFormatter(NORMAL_PATH_FILE_NAME))
      case 3 => Utility.Utils.pathFromFile(filePathFormatter(HARD_PATH_FILE_NAME))
      case 0 => Utility.Utils.pathFromFile(TowerDefenseCache.loadedMap)
    }
  }

  private def filePathFormatter(path: String): String = {
    getClass.getResource(path).getPath.replace("%20", " ")
  }

}
