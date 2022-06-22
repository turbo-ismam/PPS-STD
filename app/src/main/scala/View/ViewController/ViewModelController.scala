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

  def primaryStage_=(primaryStage: PrimaryStage): Unit = _primaryStage = Some(primaryStage)

  /**
   * This method hookup the listeners, it is called by the apply in the companion object
   * All the class that implement this trait must be instantiate only from their apply method for this reason
   */
  protected def hookupEvents(): Unit

}
