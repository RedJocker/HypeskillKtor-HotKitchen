package hotkitchen.domain.exception

class SignupInvalidPasswordException(
    override val message: String =
        "A valid password is a password that is at least six characters long and consists of letters and numbers"
): Exception(message)