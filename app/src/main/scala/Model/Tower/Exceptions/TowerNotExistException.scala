package Model.Tower.Exceptions

final class TowerNotExistException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)
