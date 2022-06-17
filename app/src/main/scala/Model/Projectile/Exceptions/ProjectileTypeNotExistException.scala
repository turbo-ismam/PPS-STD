package Model.Projectile.Exceptions

final class ProjectileTypeNotExistException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)
