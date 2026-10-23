package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridAutoRows
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.AlwaysUnderlinedLinkVariant
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.home.components.widgets.EnvironmentModule
import xyz.malefic.home.components.widgets.Fluency
import xyz.malefic.home.components.widgets.LogEntry
import xyz.malefic.home.components.widgets.SkillModule
import xyz.malefic.home.components.widgets.SpanStyle
import xyz.malefic.home.components.widgets.SystemJournal
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.ModuleSize

val HeroSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 4,
        2 to 8,
        1 to 12,
    )

val AboutTallSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 4,
        2 to 4,
        1 to 8,
    )

val AboutSmallSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 2,
        1 to 4,
        1 to 4,
    )

@Page
@Composable
fun HomePage() {
    SimpleGrid(
        numColumns(base = 1, sm = 4, md = 8, lg = 12),
        // Rows grow with their content, so larger text (or a narrow screen) never spills out of a tile
        Modifier.fillMaxWidth().gap(16.px).gridAutoRows { minmax(15.cssRem, auto) },
    ) {
        TerminalTile(
            HeroSpanStyle.toModifier(),
            "~/README.adoc",
            "[ READ_ONLY ]",
            Modifier.color(AppColors.static.signalViolet.current),
        ) {
            Column(Modifier.padding(24.px).fillMaxSize(), verticalArrangement = Arrangement.Center) {
                H1 { Text("OM GUPTA") }
                SpanText("aka malefic", AppTypography.codeMd.color(AppColors.static.primary.current).margin(top = 8.px))
                SpanText(
                    "High-performance creative engineering specializing in Kotlin, Linux ecosystems, and minimally scoped but maximal utility architectures.",
                    AppTypography.bodyLg.color(AppColors.static.onSurfaceVariant.current).margin(top = 16.px),
                )
            }
        }

        TerminalTile(
            AboutTallSpanStyle.toModifier(),
            "~/identity_node.txt",
        ) {
            Column(Modifier.padding(24.px).gap(16.px).fillMaxSize(), verticalArrangement = Arrangement.Center) {
                H2 { Text("WHOAMI") }
                SpanText(
                    "I'm a developer focused on internals and devtools, but with a wide range of experience in various fields. I'm currently pursuing an AB in Computer Science at Harvard University, and am always looking for new opportunities to learn and grow as well as people to collaborate with!",
                    AppTypography.bodyMd,
                )
            }
        }

        TerminalTile(
            AboutSmallSpanStyle.toModifier(),
            "contact_node.txt",
        ) {
            Column(Modifier.padding(16.px).gap(8.px)) {
                H2 { Text("CONNECT") }
                SpanText("DISCORD: ._malefic_.", AppTypography.codeSm)
                Link(
                    "https://github.com/OmyDaGreat",
                    "GITHUB: @OmyDaGreat",
                    AppTypography.codeSm,
                    variant = AlwaysUnderlinedLinkVariant,
                )
                Link(
                    "https://linkedin.malefic.xyz",
                    "LINKEDIN: Om Gupta",
                    AppTypography.codeSm,
                    variant = AlwaysUnderlinedLinkVariant,
                )
            }
        }

        SkillModule(
            "~/code/dialect",
            listOf(
                "Kotlin" to Fluency.DAILY,
                "Java" to Fluency.FLUENT,
                "OCaml" to Fluency.FLUENT,
                "Python" to Fluency.COMFORTABLE,
            ),
            size = ModuleSize.SMALL,
        )

        EnvironmentModule(
            "/usr/bin",
            listOf(
                "AI AGENTS",
                "DOCKER",
                "INTELLIJ IDEA",
                "ANDROID STUDIO",
                "KOBWEB",
                "HTTP4K",
                "KTOR",
                "COMPOSE",
            ),
            size = ModuleSize.MEDIUM,
        )

        EnvironmentModule(
            "/sys/env",
            listOf("LINUX", "WINDOWS", "MAC", "MOBILE", "WEB"),
            size = ModuleSize.SMALL,
        )

        SystemJournal(
            listOf(
                LogEntry("0.0001", "kernel", "Initializing web-based terminal compositor"),
                LogEntry("0.0425", "systemd", "Started User Manager for UID 1000..."),
                LogEntry("0.1258", "malefic", "Loading portfolio modules: bio, projects, stack..."),
                LogEntry("1.5522", "malefic", "Deployment ready. Awaiting user interaction."),
            ),
            size = ModuleSize.LARGE,
        )
    }
}
