package xyz.malefic.home.util

enum class TopLevelPages(
    val value: String,
    val route: String,
    val commitMessage: String,
) {
    INDEX("Index", "/", "readme & identity"),
    WORK("Work", "/work", "projects & libraries"),
    ;

    /** A stable, fake short hash for the side nav's git log. */
    val hash: String
        get() =
            (value.hashCode().toUInt() * 2654435761u)
                .toString(16)
                .padStart(7, '0')
                .take(7)

    fun isCurrentPage(currentRoute: String): Boolean {
        fun String.normalize() = "/" + this.trim().removePrefix("/").removeSuffix("/")
        return currentRoute.normalize() == route.normalize()
    }
}
