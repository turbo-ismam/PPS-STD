package Utility

import Logger.LogHelper
import scalafx.scene.image.{Image, ImageView}

import java.io.{File, FileInputStream}

object Utils extends LogHelper{

  def uploadImage(name: String): ImageView = {
    val file = new File(getClass.getResource(name).getPath.replace("%20", " "))
    new ImageView(new Image(new FileInputStream(file)))
  }

}
