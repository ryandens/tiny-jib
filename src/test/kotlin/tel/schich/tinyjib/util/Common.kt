package tel.schich.tinyjib.util

const val MINIMUM_SUPPORTED_GRADLE_VERSION = "8.2"

internal fun getProp(name: String): String {
    val fullName = "tinyjib.$name"
    return System.getProperty(fullName, null)?.trim()?.ifEmpty { null }
        ?: error("Property $fullName not set!")
}

internal fun escapeKotlinString(s: String): String {
    return s.replace("\\", "\\\\").replace("\"", "\\\"")
}
