package Utility

import Logger.LogHelper
import scalafx.scene.image.{Image, ImageView}

import java.io.{File, FileInputStream}
import java.net.{URI, URLDecoder}
import scala.util.Random

object Utils extends LogHelper{

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

  def mapGameDifficult (difficultChoice: String): Int = {
    difficultChoice match {
      case "Base" => 1
      case "Normal" => 2
      case "Hard" => 3
      case _ => 1
    }
  }

  def normalize(x: Double, y:Double): Double = {
    Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0))
  }

  def getRandomName(): String = {
    RandomName(Random.nextInt(RandomName.maxId)).toString
  }
}
