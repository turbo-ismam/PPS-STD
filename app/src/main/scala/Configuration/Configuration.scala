package Configuration

import Logger.LogHelper

import java.io.FileNotFoundException
import java.net.URL
import java.util.Properties
import scala.io.Source

class Configuration() extends LogHelper {

  def loadConfiguration() : Properties = {
    val prop: Properties = new Properties()
    // users set this property specifying the file application.properties (if custom) otherwise use the default one
    val config: String = System.getProperty("configuration.path")
    var configurationPath: Option[URL] = None

    configurationPath = if (config == null)
      Some(this.getClass.getClassLoader.getResource("application.properties")) else Some(new URL(config))

    try {
      configurationPath match {
        case Some(cp) =>
          val source = Source.fromURL(cp)
          prop.load(source.bufferedReader())
      }
    } catch {
      case _: FileNotFoundException =>
        println("Configuration file " + configurationPath + " does not exist")
        System.exit(1)
    }
    prop
  }

}

object Configuration extends LogHelper {
  var properties: Properties = new Properties();

  def getProperties: Properties = {
    properties
  }

  def addProperty(key: String, value: String): Unit = {
    logger.info("Add property {}={} to configuration ", key, value)
    properties.put(key, value)
  }

  def setProperty(key: String, value: String): Unit = {
    logger.info("Set property {}={} to configuration ", key, value)
    properties.setProperty(key, value)
  }

  def getProperty(key: String, defaultValue: String): String = {
    properties.getProperty(key, defaultValue)
  }

  def getInt(key: String, defaultValue: Int): Int = {
    val property: String = getProperty(key, defaultValue.toString)
    if (property == null) {
      return defaultValue
    }
    property.toInt
  }

  def getLong(key: String, defaultValue: Long): Long = {
    val property: String = getProperty(key, defaultValue.toString)
    if (property == null) {
      return defaultValue
    }
    property.toLong
  }

  def getString(key: String, defaultValue: String): String = {
    val property: String = getProperty(key, defaultValue)
    if (property == null) {
      return defaultValue
    }
    property
  }

  def getBoolean(key: String, defaultValue: Boolean): Boolean = {
    val property: String = getProperty(key, defaultValue.toString)
    if (property == null) {
      return defaultValue
    }
    property.toBoolean
  }

  def apply(): Unit = {
    Configuration.properties = new Configuration().loadConfiguration()
  }
}
