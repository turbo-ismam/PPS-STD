package Utility

import Logger.LogHelper
import scalafx.scene.image.{Image, ImageView}

import java.net.URL
import scala.util.Random


object Utils extends LogHelper {

  def getImageViewFromResource(name: String): ImageView = {
    val resourceFile: URL = getClass.getClassLoader().getResource(name)
    new ImageView(new Image(resourceFile.openStream()))
  }

  def getImageFromResource(name: String): Image = {
    val resourceFile: URL = getClass.getClassLoader().getResource(name)
    new Image(resourceFile.openStream())
  }

  def mapGameDifficult(difficultChoice: String): Int = {
    difficultChoice match {
      case "Base" => 1
      case "Normal" => 2
      case "Hard" => 3
      case _ => 1
    }
  }

  def getRandomName(): String = {
    RandomName(Random.nextInt(RandomName.maxId)).toString
  }

  def isJsonFileCheck(fileNamePath: String): Boolean = {
    if (fileNamePath.endsWith(".json")) true else false
  }

}
