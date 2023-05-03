package hotkitchen.domain.exception

class UnauthorizedAccessException(
    override val message: String = "Unauthorized Access"): Exception(message)