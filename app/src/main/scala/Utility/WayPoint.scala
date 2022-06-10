package Utility

import scala.util.Random

object WayPoint {
  val rand = new Random()

  def random(): WayPoint = {
    new WayPoint(rand.nextDouble(), rand.nextDouble())
  }
}

class WayPoint(posX: Double, posY: Double) {
  var x = posX
  var y = posY

  def ==(position: WayPoint): Boolean = {
    x == position.x && y == position.y
  }

  def !=(position: WayPoint): Boolean = {
    x != position.x || y != position.y
  }

  def +(position: WayPoint): WayPoint = {
    new WayPoint(x + position.x, y + position.y)
  }

  def -(position: WayPoint): WayPoint = {
    new WayPoint(x - position.x, y - position.y)
  }

  def +=(position: WayPoint): WayPoint = {
    x += position.x
    y += position.y
    this
  }

  def *(scalar: Double): WayPoint = {
    new WayPoint(x * scalar, y * scalar)
  }

  def /(scalar: Double): WayPoint = {
    new WayPoint(x / scalar, y / scalar)
  }

  def &(vect: WayPoint): Double = {
    vect.x * x + vect.y * y
  }

  def norm(): Double = {
    Math.sqrt(Math.pow(this.x, 2.0) + Math.pow(this.y, 2.0))
  }

  def normalize(): WayPoint = {
    val n = norm()
    new WayPoint(x / n, y / n)
  }

  def distance_to(position: WayPoint): Double = {
    math.sqrt(
      math.pow(x - position.x, 2)
        + math.pow(y - position.y, 2)
    )
  }

  override def toString: String = {
    "(" + x.toString + ", " + y.toString + ")"
  }

}
