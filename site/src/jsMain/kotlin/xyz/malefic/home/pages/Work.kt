package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.layouts.NavBarLayout
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.ModuleSize

@Page
@Composable
fun WorkPage() {
    NavBarLayout {
        SimpleGrid(numColumns(base = 1, lg = 6), Modifier.fillMaxSize().gap(16.px)) {
            TerminalTile(
                title = "~/projects/kanman",
                status = "[ RUNNING ]",
                size = ModuleSize.LARGE
            ) {
                Column(Modifier.padding(24.px).gap(16.px)) {
                    SpanText("KANMAN", AppTypography.headlineMd)
                    SpanText(
                        "Kotlin-based Kanban tool with TUI support and Postgres backend. " +
                                "Focused on extreme productivity and zero-latency drag-and-drop operations.",
                        AppTypography.bodyMd
                    )
                }
            }
        }
    }
}
