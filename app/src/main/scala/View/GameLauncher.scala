package View

import Configuration.Configuration
import Logger.LogHelper
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.{PrimaryStage, Stage}

object GameLauncher extends JFXApp3 with LogHelper{

  override def start(): Unit = {

    Configuration()

    val primaryStage: PrimaryStage =  new PrimaryStage {
      title = Configuration.getString("ProjectName", "Tower Defense the GAME")
    }

    GameView.setStage(primaryStage)

    primaryStage.scene = GameView.mainMenuScene

    GameView.hookupEvents()

  }
}
