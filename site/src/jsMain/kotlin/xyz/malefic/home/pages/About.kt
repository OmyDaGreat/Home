package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.widgets.SpanStyle
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppTypography

val AboutTallSpanStyle = SpanStyle(4, 4)

val AboutSmallSpanStyle = SpanStyle(2, 2)

@Page
@Composable
fun AboutPage() =
    SimpleGrid(numColumns(base = 1, lg = 6), Modifier.fillMaxSize().gap(16.px)) {
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
                Link("https://github.com/OmyDaGreat", "GITHUB: @OmyDaGreat", AppTypography.codeSm)
            }
        }
    }
