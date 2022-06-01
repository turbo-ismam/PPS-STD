package View

import Configuration.ConfigurationReader
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage

object GameLauncher extends JFXApp3{

  override def start(): Unit = {

    val c = new ConfigurationReader()

    GameView.hookupEvents()

    stage = new PrimaryStage {
      title = System.getProperty("ProjectName", "Tower Defense the GAME")
      scene = GameView.scene
    }
  }
}
