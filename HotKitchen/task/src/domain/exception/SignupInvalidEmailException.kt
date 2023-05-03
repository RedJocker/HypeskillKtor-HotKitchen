package hotkitchen.domain.exception

class SignupInvalidEmailException(
    override val message: String =
        "A valid email address consists of an email prefix and an email domain, both in acceptable formats. " +
                "Use only letters, numbers, and special symbols:"
): Exception(message)