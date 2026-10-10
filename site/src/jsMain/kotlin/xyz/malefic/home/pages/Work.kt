package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridAutoRows
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.AlwaysUnderlinedLinkVariant
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.widgets.SpanStyle
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppTypography

val WorkLargeSpanStyle =
    SpanStyle(
        1 to 1,
        1 to 2,
        2 to 4,
        2 to 6,
    )

@Page
@Composable
fun WorkPage() {
    SimpleGrid(
        numColumns(base = 2, sm = 4, md = 8, lg = 12),
        Modifier
            .fillMaxWidth()
            .gap(16.px)
            .gridAutoRows { size(120.px) },
    ) {
        TerminalTile(
            title = "~/projects/kanman",
            status = "[ RUNNING ]",
            modifier = WorkLargeSpanStyle.toModifier(),
        ) {
            Column(Modifier.padding(24.px).gap(16.px)) {
                Link("https://github.com/OmyDaGreat/KanMan/", "KANMAN", AppTypography.headlineMd, variant = AlwaysUnderlinedLinkVariant)
                SpanText(
                    "Another™ Kanban task manager, made with Http4k and Kobweb, supporting alternative clients, a public/private board invitation & role system, and a helpful personal dashboard.",
                    AppTypography.bodyMd,
                )
            }
        }
    }
}
