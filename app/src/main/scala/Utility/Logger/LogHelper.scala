package Utility.Logger

import org.apache.logging.log4j.{LogManager, Logger}

trait LogHelper {
  val loggerName: String = this.getClass.getName
  lazy val logger: Logger = LogManager.getLogger(loggerName)
}
