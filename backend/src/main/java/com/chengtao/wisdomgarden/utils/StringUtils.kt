class StringUtils {
    companion object {
        fun isStringNull(vararg stings: String): Boolean {
            for (s: String in stings) {
                if (s == null || s.length == 0) {
                    return true
                }
            }
            return false
        }
    }
}