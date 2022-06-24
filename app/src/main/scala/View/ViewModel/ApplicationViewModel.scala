package View.ViewModel

import scalafx.scene.Scene

/**
 * This interface represent a generic Model of a scene
 * It contain only the definition of the scene
 * This interface must be implemented by another interface that describe specific the scene behaviour
 */
trait ApplicationViewModel {

  def scene: Scene

}
