package hotkitchen.util

object Validation {

    fun String.isNotValidEmail(): Boolean {
        return "(\\w+[\\w.\\-])*\\w+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,}".toRegex().matches(this).not()
    }

    fun String.isNotValidPass(): Boolean {
        return length < 6 || containsNoDigits() || containsNoLetter()
    }

    private fun String.containsNoDigits(): Boolean {
        return firstOrNull(Char::isDigit) == null
    }

    private fun String.containsNoLetter(): Boolean {
        return firstOrNull(Char::isLetter) == null
    }
}