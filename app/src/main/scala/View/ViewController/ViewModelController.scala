package View.ViewController

import scalafx.application.JFXApp3.PrimaryStage

/**
 * This interface is a generalization of all view controllers.
 * All of them got their inherited primary stage.
 * The first instanced of view inherit from the GameLauncher, then is passed from one to another one during the scene
 * swap.
 */
trait ViewModelController {

  private var _primaryStage: Option[PrimaryStage] = None

  def primaryStage(): Option[PrimaryStage] = _primaryStage

  def primaryStage_(primaryStage: PrimaryStage): Unit = _primaryStage = Some(primaryStage)
}
