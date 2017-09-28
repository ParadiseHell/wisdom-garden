class StringUtils {
    companion object {
        fun isStringNull(vararg stings: String): Boolean {
            return stings.any { it == null || it.length == 0 }
        }
    }
}