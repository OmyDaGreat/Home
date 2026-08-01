package xyz.malefic.home.util

enum class TopLevelPages(
    val value: String,
    val route: String,
) {
    INDEX("Index", "/"),
    WORK("Work", "/work"),
    ABOUT("About", "/about"),
    ;

    fun isCurrentPage(currentRoute: String): Boolean {
        fun String.normalize() = "/" + this.trim().removePrefix("/").removeSuffix("/")
        return currentRoute.normalize() == route.normalize()
    }
}
