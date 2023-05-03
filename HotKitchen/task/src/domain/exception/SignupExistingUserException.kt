package hotkitchen.domain.exception

class SignupExistingUserException(
    override val message: String = "There is already an user with that email registered"): Exception(message)