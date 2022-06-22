package View.ViewController

/**
 * This abstract class is used to protect the method that hookup action listeners, because it must be used only
 * from the controller class.
 */
abstract class AbstractViewModelController extends ViewModelController {

  /**
   * This method hookup the listeners, it is called by the apply in the companion object
   * All the class that implement this abstract class must be instantiate only from their apply method for this reason
   */
  protected def hookupEvents(): Unit
}
