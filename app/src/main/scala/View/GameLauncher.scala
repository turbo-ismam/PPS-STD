package View

import Configuration.Configuration
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage

object GameLauncher extends JFXApp3 {

  override def start(): Unit = {

    //Initialize configuration
    Configuration()

    GameView.hookupEvents()

    stage = new PrimaryStage {
      title = Configuration.getString("ProjectName", "Tower Defense the GAME")
      scene = GameView.scene
    }
  }
}
