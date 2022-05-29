package Configuration

import java.io.FileNotFoundException
import java.net.URL
import java.util.Properties
import scala.io.Source

class ConfigurationReader() {

  val properties: Properties = new Properties()

  // users set this property specifying the file application.properties (if custom) otherwise use the default one
  var config: String = System.getProperty("configuration.path")
  var configurationPath: Option[URL] = None

  configurationPath = if (config == null)
    Some(this.getClass.getClassLoader.getResource("application.properties")) else Some(new URL(config))

  try {
    configurationPath match {
      case Some(cp) =>
        val source = Source.fromURL(cp)
        properties.load(source.bufferedReader())
        properties.forEach((k, v) => System.setProperty(k.toString, v.toString))
    }
  } catch {
    case _: FileNotFoundException =>
      println("Configuration file " + configurationPath + " does not exist")
      System.exit(1)
  }


}
