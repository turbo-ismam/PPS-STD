package Utility

import Logger.LogHelper
import com.google.gson.Gson
import scalafx.scene.image.{Image, ImageView}

import java.io.{BufferedReader, File, FileInputStream, FileReader}
import scala.collection.mutable.ArrayBuffer

object Utils extends LogHelper{

  def getImageViewFromResource(name: String): ImageView = {
    val file = new File(getClass.getResource(name).getPath.replace("%20", " "))
    new ImageView(new Image(new FileInputStream(file)))
  }

  def getImageFromResource(name: String): Image = {
    val file = new File(getClass.getResource(name).getPath.replace("%20", " "))
    new Image(new FileInputStream(file))
  }

  def pathFromFile(fileNamePath: String): Array[Array[Int]] = {
    val gson = new Gson();
    val reader = new BufferedReader(new FileReader(fileNamePath))
    gson.fromJson(reader,classOf[SimplePathJsonObject]).map.map(y => y.map(x => x.toInt))
  }

}
