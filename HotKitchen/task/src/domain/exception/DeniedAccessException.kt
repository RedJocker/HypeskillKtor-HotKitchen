package hotkitchen.domain.exception

class DeniedAccessException(
    override val message: String = "Access denied"): Exception(message)