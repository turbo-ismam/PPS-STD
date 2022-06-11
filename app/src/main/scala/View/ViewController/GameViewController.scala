package View.ViewController

import Cache.TowerDefenseCache
import Configuration.DefaultConfig.{GO_MAIN_MENU_BTN_ID, NOT_IMPLEMENTED_YET, RESTART_GAME_BTN_ID, START_WAVE_BTN_ID}
import Controller.GameController
import Logger.LogHelper
import Model.Tower.TowerTypes.{BASE_TOWER, CANNON_TOWER, FLAME_TOWER}
import View.EventHandlers
import View.ModelView.GameViewModel
import javafx.scene.media.{Media, MediaPlayer}
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.input.MouseEvent

import java.nio.file.Paths

class GameViewController() extends ViewModelController with LogHelper {

  private val _gameViewModel: GameViewModel = GameViewModel.apply()
  val thread = new Thread {
    val hit: Media = new Media(Paths.get("C:\\Users\\Vlad\\Downloads\\PortableGit\\ScalaTowerDefense\\app\\src\\main\\resources\\music\\wave_music.mp3").toUri().toString())
    val mediaPlayer: MediaPlayer = new MediaPlayer(hit)
    mediaPlayer.play()
  }
  thread.start()

  def hookupEvents(): Unit = {

    // bottom buttons action listeners
    _gameViewModel.buttons().foreach(button => {
      button.getId match {
        case START_WAVE_BTN_ID => button.setOnAction(EventHandlers.startWave())
        case GO_MAIN_MENU_BTN_ID => button.setOnAction(EventHandlers.goMainMenu(this.primaryStage()))
        case RESTART_GAME_BTN_ID => button.setOnAction(EventHandlers.nothing())
      }
    })

    // tower toggle button action listeners
    _gameViewModel.towerToggleButtons().foreach(toggleButton => {
      toggleButton.getId match {
        case "baseTower" => toggleButton.setOnAction(EventHandlers.selectTower(BASE_TOWER))
        case "cannonTower" => toggleButton.setOnAction(EventHandlers.selectTower(CANNON_TOWER))
        case "flameTower" => toggleButton.setOnAction(EventHandlers.selectTower(FLAME_TOWER))
        case _ => logger.warn(NOT_IMPLEMENTED_YET)
      }
    })

    _gameViewModel.canvas().addEventHandler(MouseEvent.MouseClicked,
      EventHandlers.onCellClickedEventHandler())
  }

  def gameViewModel(): GameViewModel = _gameViewModel
}

object GameViewController {

  private var _game_view_model: Option[GameViewModel] = None

  def game_view_model: Option[GameViewModel] = _game_view_model

  private def game_view_model_=(gameViewModel: Option[GameViewModel]): Unit = {
    _game_view_model = gameViewModel
  }

  def apply(primaryStage: PrimaryStage): GameViewController = {
    val gameViewController = new GameViewController()
    game_view_model = Option(gameViewController.gameViewModel())
    gameViewController.primaryStage_(primaryStage)
    gameViewController.hookupEvents()
    gameViewController
  }
}
