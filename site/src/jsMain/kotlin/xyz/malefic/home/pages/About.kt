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

val AboutTallSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 4,
        2 to 8,
        2 to 8,
    )

val AboutSmallSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 2,
        2 to 4,
        2 to 4,
    )

@Page
@Composable
fun AboutPage() =
    SimpleGrid(
        numColumns(base = 2, sm = 4, md = 8, lg = 12),
        Modifier
            .fillMaxWidth()
            .gap(16.px)
            .gridAutoRows { size(120.px) },
    ) {
        TerminalTile(
            title = "~/about.sh",
            modifier = AboutTallSpanStyle.toModifier(),
        ) {
            Column(Modifier.padding(24.px).gap(16.px)) {
                SpanText("Identity_Node", AppTypography.displayLg)
                SpanText(
                    "I am a software architect specializing in low-level infrastructure and functional programming. " +
                        "My workflow is built on technical discipline and terminal-driven efficiency.",
                    AppTypography.bodyMd,
                )
            }
        }

        TerminalTile(
            title = "contact_nodes.txt",
            modifier = AboutSmallSpanStyle.toModifier(),
        ) {
            Column(Modifier.padding(16.px).gap(8.px)) {
                SpanText("DISCORD: ._malefic_.", AppTypography.codeSm)
                Link("https://github.com/OmyDaGreat", "GITHUB: @OmyDaGreat", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
                Link("https://linkedin.malefic.xyz", "LINKEDIN: Om Gupta", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
            }
        }
    }
