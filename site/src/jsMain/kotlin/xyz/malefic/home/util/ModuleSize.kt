package xyz.malefic.home.util

enum class ModuleSize(val colSpan: Int, val rowSpan: Int) {
    SMALL(2, 1),
    MEDIUM(4, 2),
    LARGE(6, 2),
    TALL(4, 4),
    FULL(6, 4)
}
