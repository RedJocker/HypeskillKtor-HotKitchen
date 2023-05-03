package hotkitchen.domain.exception

class InvalidDeleteUserRequestException(
    override val message: String = "Invalid delete user request"): Exception(message)
