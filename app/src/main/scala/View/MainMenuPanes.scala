package View

import Logger.LogHelper
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, ComboBox}
import scalafx.scene.layout.VBox

object MainMenuPanes extends LogHelper{

  private val startGameButton: Button = new Button {
    text = "Start Game!"
  }

  val comboBox = new ComboBox(List("Easy","Normal","Hard"))

  private val exitGame: Button = new Button {
    text = "Exit Game"
  }

  private val options = new VBox {
    padding = Insets(10)
    spacing = 10
    alignment = Pos.Center
    children = List(
      startGameButton,
      comboBox,
      exitGame
    )

  }

  def getCentralPane: VBox = {
    options
  }

  def getButtons: Array[Button] = Array(startGameButton, exitGame)


}
