package View.EventHandlers

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Tower.TowerType
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import scalafx.scene.canvas.GraphicsContext

class EventsHandler(val graphicsContext: GraphicsContext) extends LogHelper {

  val gc: GraphicsContext = graphicsContext

  def tileClickEventHandler: EventHandler[MouseEvent] = {
    (event: MouseEvent) => {
      logger.info("Click event - X:" + event.getX + " - Y: " + event.getY)
      val image = new Tower(TowerType.deserialize(TowerType.BASE_TOWER), event.getX, event.getY, "").graphic()
      gc.drawImage(image, event.getX, event.getY, 64 , 64)
    }
  }

}
