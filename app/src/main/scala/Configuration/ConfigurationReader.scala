package Configuration

import java.io.{FileNotFoundException}
import java.net.URL
import java.util.Properties
import scala.io.Source

class ConfigurationReader() {

  val properties: Properties = new Properties()

  // users set this property specifying the file application.properties (if custom) otherwise we use default
  var config: String = System.getProperty("configuration.path")
  var configurationPath: URL = this.getClass.getClassLoader.getResource("application.properties")

  if (config != null) {
    configurationPath = new URL(config)
  }


  try {
    val source = Source.fromURL(configurationPath)
    properties.load(source.bufferedReader())
    properties.forEach((k,v) => System.setProperty(k.toString,v.toString))
  } catch {
    case e: FileNotFoundException =>
      println("Configuration file " + configurationPath + " does not exist")
      System.exit(1)
  }



}
