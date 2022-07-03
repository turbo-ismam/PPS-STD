package View.EventHandlers

import Controller.{DrawingManager, GameController, UpdateManager}
import Model.Tower.TowerTypes
import Model.Tower.TowerTypes._
import Utility.Cache.TowerDefenseCache
import Utility.Configuration.DefaultConfig._
import Utility.Utils
import View.ViewController.{GameViewController, MainMenuViewController}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.input.MouseEvent
import scalafx.application.JFXApp3.PrimaryStage

/**
 * Trait to handle the GameEventHandler
 * It contains mainly the game events and two other buttons:
 * 1. Game events:
 *     a. cell click to place tower
 *        b. tower button click to select a tower
 *        c. start wave
 *        d. restart game
 *        2. go main menu
 */
trait GameEventHandlers extends EventHandlers {

  /**
   * Handle the grid cell click (for placing tower)
   *
   * @return action listener that handle the event
   */
  def onCellClickedEventHandler: EventHandler[MouseEvent]

  /**
   * Handle the start wave button, it start the enemy spawning
   *
   * @return action listener that handle the event
   */
  def startWave: EventHandler[ActionEvent]

  /**
   * Handle the click on go main menu button, it used to go back to main menu
   *
   * @param primaryStage of the main menu
   * @return action listener that handle the event
   */
  def goMainMenu(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent]

  /**
   * Handle the click on the towers button, it is used to understand which tower is selected
   *
   * @param towerTypes of the selected tower
   * @return action listener that handle the event
   */
  def selectTower(towerTypes: TowerTypes.TowerType): EventHandler[ActionEvent]

  /**
   * Handle the click on the restart game button, it is used remove all element in the grid and start a new game
   * using the old information (player name, grid ecc.)
   *
   * @param primaryStage of the game
   * @return action listener that handle the event
   */
  def restartGame(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent]

}


object GameEventHandlers {

  /**
   * This class contains all event handler that is used in the Game View Controller
   *
   * @param gameViewController used for graphic updates
   * @param gameController     used to change the status of the game
   */
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

    override def startWave: EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        gameController.onPlayButton()
      }
    }

    override def goMainMenu(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent] = {
      (_: ActionEvent) => {
        primaryStage match {
          case None => logger.error(STAGE_ERROR)
          case Some(primaryStage: PrimaryStage) =>
            val mainMenuViewController: MainMenuViewController = MainMenuViewController.apply(primaryStage)
            primaryStage.setScene(mainMenuViewController.menuViewModel.scene)
        }
      }
    }

    override def selectTower(towerTypes: TowerTypes.TowerType): EventHandler[ActionEvent] = {
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

    override def restartGame(primaryStage: Option[PrimaryStage]): EventHandler[ActionEvent] = {
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
            setScene(stage, gameViewController, playerName.getOrElse("No Name"), difficulty.getOrElse(1))

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