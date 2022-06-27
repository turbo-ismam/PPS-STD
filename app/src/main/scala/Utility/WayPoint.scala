package Utility

/**
 * Class to manage 2D positions.
 *
 * @param posX
 * @param posY
 */
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
    WayPoint(x + position.x, y + position.y)
  }

  def -(position: WayPoint): WayPoint = {
    WayPoint(x - position.x, y - position.y)
  }

  def +=(position: WayPoint): WayPoint = {
    x += position.x
    y += position.y
    this
  }

  def +(scalar: Double): WayPoint = {
    WayPoint(x + scalar, y + scalar)
  }

  def *(scalar: Double): WayPoint = {
    WayPoint(x * scalar, y * scalar)
  }

  def /(scalar: Double): WayPoint = {
    WayPoint(x / scalar, y / scalar)
  }

  def &(vect: WayPoint): Double = {
    vect.x * x + vect.y * y
  }

  def circularRadius(range: Int): WayPoint = {
    WayPoint(x - ((range - 1) * 32), y - ((range - 1) * 32))
  }

  //abs return absolute value
  def diff_abs(position: WayPoint): WayPoint = {
    WayPoint(math.abs(x - position.x), math.abs(y - position.y))
  }

  // Hypot returns the square root of the sum of squares of its arguments
  def diff_abs_hypot(position: WayPoint): Double = {
    val abs = diff_abs(position)
    Math.hypot(abs.x, abs.y)
  }

  def compare(range: Double, cellSize: Int, position: WayPoint): Boolean = {
    (x + range * cellSize > position.x) && (x < position.x + cellSize) &&
      (y + range * cellSize > position.y) && (y < position.y + cellSize)
  }

  def distanceTo(ref: WayPoint): WayPoint = {
    WayPoint(Math.abs(x - ref.x + 32), Math.abs(y - ref.y + 32))
  }

  def totalDistance: Double = x + y

  override def clone(): WayPoint = {
    WayPoint(x, y)
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
