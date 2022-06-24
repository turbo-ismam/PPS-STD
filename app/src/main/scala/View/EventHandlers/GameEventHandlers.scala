package View.EventHandlers

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{CACHE_GENERIC_ERROR, STAGE_ERROR}
import Controller.{DrawingManager, GameController, UpdateManager}
import Model.Tower.TowerTypes
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import Utility.Utils
import View.ViewController.{GameViewController, MainMenuViewController}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.input.MouseEvent
import scalafx.application.JFXApp3.PrimaryStage

/**
 * This class contains all event handler that is used from the Game Controller
 * @param gameViewController used to graphic updates
 * @param gameController used to change the status of the game
 */
trait GameEventHandlers extends EventHandlers {

  def onCellClickedEventHandler: EventHandler[MouseEvent]

  def startWave: EventHandler[ActionEvent]

  def goMainMenu(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent]

  def selectTower(towerTypes: TowerTypes.TowerType): EventHandler[ActionEvent]

  def restartGame(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent]

}


object GameEventHandlers {

  sealed private case class GameEventHandlersImpl(gameViewController: GameViewController, gameController: GameController)
    extends GameEventHandlers {

    def onCellClickedEventHandler: EventHandler[MouseEvent] = {
      (event: MouseEvent) => {
        if (gameController.gameStarted) {
          gameController.onCellClicked(event.getX, event.getY)
          //Draw tower on tile
          val tower = gameController.selectedTower
          tower match {
            case None =>
            case Some(tower) =>
              DrawingManager.drawTower(tower.towerPosition.x, tower.towerPosition.y, tower.graphic(), gameViewController)
          }
        }
      }
    }

    def startWave: EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        gameController.onPlayButton()
      }
    }

    def goMainMenu(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        primaryStage match {
          case None => logger.error(STAGE_ERROR)
          case Some(primaryStage: PrimaryStage) =>
            val mainMenuViewController: MainMenuViewController = MainMenuViewController.apply(primaryStage)
            primaryStage.setScene(mainMenuViewController.menuViewModel.scene)
        }
      }
    }

    def selectTower(towerTypes: TowerTypes.TowerType): EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        towerTypes match {
          case BASE_TOWER =>
            val tower = gameController.availableTowers.get(BASE_TOWER)
            gameController.buildTower(tower.get)
            logger.info("Select tower {} ", tower.get.name)
          case CANNON_TOWER =>
            val tower = gameController.availableTowers.get(CANNON_TOWER)
            gameController.buildTower(tower.get)
            logger.info("Select tower {} ", tower.get.name)
          case FLAME_TOWER =>
            val tower = gameController.availableTowers.get(FLAME_TOWER)
            gameController.buildTower(tower.get)
            logger.info("Select tower {} ", tower.get.name)
          case _ =>
            logger.warn("Non implemented yet")
        }
      }
    }

    def restartGame(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent] = {
      (_: ActionEvent) => {

        val playerName = TowerDefenseCache.playerName
        val difficulty = TowerDefenseCache.difficulty

        var gameController: GameController = null

        TowerDefenseCache.gameType match {
          case Some(value) =>
            difficulty match {
              case Some(dif) =>
                if (value) gameController = GameController(playerName.getOrElse(Utils.getRandomName()), dif)
                else gameController = GameController(playerName.getOrElse(Utils.getRandomName()), dif)
              case None => logger.error(CACHE_GENERIC_ERROR)
            }
          case None => logger.error(CACHE_GENERIC_ERROR)
        }

        primaryStage match {
          case Some(stage) =>
            val gameViewController: GameViewController = GameViewController(stage, gameController)
            setScene(stage,gameViewController,playerName.getOrElse("No Name"),difficulty.getOrElse(1))

            DrawingManager.drawGrid(gameController, gameViewController)
            //start loop
            UpdateManager.apply(gameController, gameViewController).run().start()
          case None => logger.error(STAGE_ERROR)

        }
      }
    }

  }

  def apply(gameViewController: GameViewController, gameController: GameController): GameEventHandlers =
    GameEventHandlersImpl(gameViewController, gameController)
}