package hotkitchen.domain.exception

class SigninAuthorizationFail(
    override val message: String = "Failed authorization"): Exception(message)