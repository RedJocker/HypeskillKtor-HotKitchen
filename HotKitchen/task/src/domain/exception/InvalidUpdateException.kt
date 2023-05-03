package hotkitchen.domain.exception

class InvalidUpdateException(
    override val message: String = "Invalid update"): Exception(message)