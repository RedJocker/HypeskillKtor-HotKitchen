package hotkitchen.domain.exception

class InvalidUserInfoRequest(
    override val message: String = "Invalid user info request"): Exception(message)