package Grid

import Logger.LogHelper
import Model.Grid.GridController
import Model.Grid.Tiles.TileTypes
import org.scalatest.funsuite.AnyFunSuite
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx

import scala.collection.mutable.ArrayBuffer

class GridTest extends AnyFunSuite with LogHelper{

  val gridController: GridController = GridController(1)

  test("Grid"){
    gridController.grid.foreach(y => {
      logger.warn("\n\n\nRow: ")
      y.foreach(x => {
        logger.warn("{} - {} ", x.xPlace, x.yPlace)
        logger.warn(x.tType.buildable)
      })
    })
  }

  test("Tile buildable"){
    assert(gridController.isTileBuildable(0,0))
    assert(!gridController.isTileBuildable(1,1))
    assert(!gridController.isTileBuildable(3,9))
  }

  test("Drawing info"){
    val arrayBuffer: ArrayBuffer[(Color, Int, Int)] = gridController.drawingInfo

    assert(sfxColor2jfx(arrayBuffer(2)._1) == sfxColor2jfx(Color.Green))
    assert(arrayBuffer(2)._2 == 128)
    assert(arrayBuffer(2)._3 == 0)

    assert(sfxColor2jfx(arrayBuffer(21)._1) == sfxColor2jfx(Color.Grey))
    assert(arrayBuffer(21)._2 == 64)
    assert(arrayBuffer(21)._3 == 64)

    assert(sfxColor2jfx(arrayBuffer(81)._1) == sfxColor2jfx(Color.Green))
    assert(arrayBuffer(81)._2 == 64)
    assert(arrayBuffer(81)._3 == 256)

    assert(sfxColor2jfx(arrayBuffer(190)._1) == sfxColor2jfx(Color.Green))
    assert(arrayBuffer(190)._2 == 640)
    assert(arrayBuffer(190)._3 == 576)
  }

  test("Tile info"){

    assert(gridController.tile(0,0).x == 0)
    assert(gridController.tile(0,0).y == 0)
    assert(gridController.tile(0,0).tType.tileType == TileTypes.Grass)
    assert(gridController.tile(0,0).xPlace == 0)
    assert(gridController.tile(0,0).yPlace == 0)


    assert(gridController.tile(12,7).x == 768)
    assert(gridController.tile(12,7).y == 448)
    assert(gridController.tile(12,7).tType.tileType == TileTypes.Grass)
    assert(gridController.tile(12,7).xPlace == 12)
    assert(gridController.tile(12,7).yPlace == 7)

    assert(gridController.tile(3,1).x == 192)
    assert(gridController.tile(3,1).y == 64)
    assert(gridController.tile(3,1).tType.tileType == TileTypes.Path)
    assert(gridController.tile(3,1).xPlace == 3)
    assert(gridController.tile(3,1).yPlace == 1)
  }

  test("Check for start tile"){
    // 0-1
    gridController.tileStartOrEnd(TileTypes.StartTile) match {
      case Some(tile) => assert(tile.x == 0 && tile.y == 64)
      case None => assert(condition = false)
    }
  }

  test("Check for end tile"){
    // 19-5
    gridController.tileStartOrEnd(TileTypes.EndTile) match {
      case Some(tile) => assert(tile.x == 1216 && tile.y == 320)
      case None => assert(condition = false)
    }
  }

  test("Grid with wrong path"){
    //assert(new GridController(3).gameGrid == null)
    val q = Array(Array(1,2,3), Array(1,2,4), Array(1,5,7))
      .flatMap(_.toSeq).groupBy(identity).view.mapValues(_.length)(2) match {
      case 1 => println("Ok")
      case _ => println("NO")
    }
  }




}
