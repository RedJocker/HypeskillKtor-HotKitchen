package hotkitchen.domain.exception

class InvalidCategoryIdException(
    override val message: String = "Invalid mealId, mealId should be an integer number"): Exception(message)