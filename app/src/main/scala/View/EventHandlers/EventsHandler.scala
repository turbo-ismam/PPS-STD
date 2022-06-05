package View.EventHandlers

import Cache.TowerDefenseCache
import Controller.Tower.Tower
import Logger.LogHelper
import Model.Player
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Model.Tower.{TowerType, TowerTypes}
import View.{GamePanes, GameView}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.input.MouseEvent
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

class EventsHandler(val graphicsContext: GraphicsContext) extends LogHelper {

  val gc: GraphicsContext = graphicsContext

  def createTowerEventHandler(towerTypes: TowerTypes.TowerType): EventHandler[MouseEvent] = {
    (event: MouseEvent) => {
      towerTypes match {
        case BASE_TOWER =>
          designTower(event, BASE_TOWER)
        case CANNON_TOWER =>
          designTower(event, CANNON_TOWER)
        case FLAME_TOWER =>
          designTower(event, FLAME_TOWER)
        case _ => None
      }
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

  def selectTower(towerTypes: TowerTypes.TowerType): EventHandler[ActionEvent] = {
    (_: ActionEvent) => {
      towerTypes match {
        case BASE_TOWER =>
          TowerDefenseCache.setSelectedTower(BASE_TOWER)
        case CANNON_TOWER =>
          TowerDefenseCache.setSelectedTower(CANNON_TOWER)
        case FLAME_TOWER =>
          TowerDefenseCache.setSelectedTower(FLAME_TOWER)
        case _ =>
          logger.warn("Non implemented yet")
      }
    }
  }

  private def designTower(event: MouseEvent, towerTypes: TowerTypes.TowerType): Unit = {
    //logger.info("Click event - X:" + event.getX + " - Y: " + event.getY)
    val gameController = GameView.gameController
    val player: Player = gameController.player
    val tower = new Tower(TowerType(towerTypes), player, event.getX, event.getY, GameView.gameController)
    val image = tower.graphic()
    gameController += tower
    gc.drawImage(image, event.getX, event.getY, 64, 64)
  }


}
