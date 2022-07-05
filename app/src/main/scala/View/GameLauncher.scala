package View

import Utility.Configuration.{Configuration, DefaultConfig}
import View.ViewController.MainMenuViewController
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage

object GameLauncher extends JFXApp3 {

  override def start(): Unit = {

    //Initialize configuration
    Configuration()

    val primaryStage: PrimaryStage = new PrimaryStage {
      title = DefaultConfig.PROJECT_NAME
    }

    val mainMenuViewController: MainMenuViewController = MainMenuViewController.apply(primaryStage)

    primaryStage.scene = mainMenuViewController.menuViewModel.scene

  }
}
