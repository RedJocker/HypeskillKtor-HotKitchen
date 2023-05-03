package hotkitchen.domain.exception

class DuplicateCategoryException(
    override val message: String = "There is already a category with this id registered"): Exception(message)