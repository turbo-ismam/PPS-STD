package Utility

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

  def +(scalar: Double): WayPoint = {
    new WayPoint(x + scalar, y + scalar)
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

  override def toString: String = {
    "(" + x.toString + ", " + y.toString + ")"
  }

}

object WayPoint {

  def apply(posX: Double, posY: Double): WayPoint = {
    val wayPoint: WayPoint = new WayPoint(posX, posY)
    wayPoint
  }
}
