package Utility

import Logger.LogHelper
import com.google.gson.Gson
import scalafx.scene.image.{Image, ImageView}

import java.io.{BufferedReader, FileInputStream, FileReader}
import java.net.URI
import scala.util.Random


object Utils extends LogHelper {

  def getImageViewFromResource(name: String): ImageView = {
    val resourceFile = getClass.getResource(name).getPath
    val uri: URI = new URI(resourceFile)
    new ImageView(new Image(new FileInputStream(uri.getPath)))
  }

  def getImageFromResource(name: String): Image = {
    val resourceFile = getClass.getResource(name).getPath
    val uri: URI = new URI(resourceFile)
    new Image(new FileInputStream(uri.getPath))
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
