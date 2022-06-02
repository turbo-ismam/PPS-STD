package View.EventHandlers

import Controller.Tower.Tower
import Logger.LogHelper
import Model.Tower.TowerType
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.input.MouseEvent
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

class EventsHandler(val graphicsContext: GraphicsContext) extends LogHelper {

  val gc: GraphicsContext = graphicsContext

  def tileClickEventHandler: EventHandler[MouseEvent] = {
    (event: MouseEvent) => {
      logger.info("Click event - X:" + event.getX + " - Y: " + event.getY)
      val image = new Tower(TowerType.deserialize(TowerType.BASE_TOWER), event.getX, event.getY, "").graphic()
      gc.drawImage(image, event.getX, event.getY, 64 , 64)
    }
  }

  def unimplementedButtonAlert: EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      new Alert(AlertType.Error, "Unimplemented button").showAndWait()
    }
  }

  def changeScene(primaryStage: PrimaryStage, scene: Scene): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      primaryStage.setScene(scene)
    }
  }

}
