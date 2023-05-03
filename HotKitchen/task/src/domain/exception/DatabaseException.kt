package hotkitchen.domain.exception

class DatabaseException(
    override val message: String = "Database failed") : Exception(message)
