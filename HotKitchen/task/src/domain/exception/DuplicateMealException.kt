package hotkitchen.domain.exception

class DuplicateMealException(
    override val message: String = "There is already a meal with this id registered"): Exception(message)