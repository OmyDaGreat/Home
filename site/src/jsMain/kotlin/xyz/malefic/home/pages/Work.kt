package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.gridRow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppTypography

val WorkLargeSpanStyle =
    CssStyle {
        base { Modifier.gridColumn("span 1") }
        Breakpoint.LG { Modifier.gridColumn("span 6").gridRow("span 3") }
    }

@Page
@Composable
fun WorkPage() {
    SimpleGrid(numColumns(base = 1, lg = 6), Modifier.fillMaxSize().gap(16.px)) {
        TerminalTile(
            title = "~/projects/kanman",
            status = "[ RUNNING ]",
            modifier = WorkLargeSpanStyle.toModifier(),
        ) {
            Column(Modifier.padding(24.px).gap(16.px)) {
                SpanText("KANMAN", AppTypography.headlineMd)
                SpanText(
                    "Kotlin-based Kanban tool with TUI support and Postgres backend. " +
                        "Focused on extreme productivity and zero-latency drag-and-drop operations.",
                    AppTypography.bodyMd,
                )
            }
        }
    }
}
