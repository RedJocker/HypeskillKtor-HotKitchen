package hotkitchen.domain.exception

class UserInfoNotFoundException(
    override val message: String = "Could not find user info"): Exception(message)
