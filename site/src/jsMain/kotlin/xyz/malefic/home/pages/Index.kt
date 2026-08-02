package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridAutoRows
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.layouts.MainLayout
import xyz.malefic.home.components.sections.BootSequence
import xyz.malefic.home.components.widgets.EnvironmentModule
import xyz.malefic.home.components.widgets.LogEntry
import xyz.malefic.home.components.widgets.SkillModule
import xyz.malefic.home.components.widgets.SpanStyle
import xyz.malefic.home.components.widgets.SystemJournal
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.ModuleSize

var isBooting by mutableStateOf(true)

val HeroSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 2,
        smCol = 2,
        smRow = 3,
        mdCol = 4,
        mdRow = 2,
        lgCol = 6,
        lgRow = 1,
    )

@Page
@Layout(".components.layouts.NoLayout")
@Composable
fun HomePage() {
    if (isBooting) {
        BootSequence { isBooting = false }
    } else {
        MainLayout {
            SimpleGrid(
                numColumns(base = 1, sm = 2, md = 4, lg = 6),
                Modifier
                    .fillMaxSize()
                    .gap(16.px)
                    .gridAutoRows { size(240.px) },
            ) {
                TerminalTile(
                    HeroSpanStyle.toModifier(),
                    "~/README.md",
                    "[ READ_ONLY ]",
                    Modifier.color(AppColors.static.signalViolet.current),
                ) {
                    Column(
                        Modifier.padding(24.px).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        SpanText("MALEFIC_PORTFOLIO", AppTypography.displayLg)
                        SpanText(
                            "High-performance creative engineering specializing in Kotlin, Linux ecosystems, and minimal UI architectures.",
                            AppTypography.bodyLg.color(AppColors.static.onSurfaceVariant.current).margin(top = 16.px),
                        )
                    }
                }

                SkillModule(
                    "~/code/dialect",
                    listOf("KOTLIN" to 95, "JAVA" to 80, "PYTHON" to 60, "JAVASCRIPT" to 60),
                    size = ModuleSize.SMALL,
                )

                EnvironmentModule(
                    "/usr/bin",
                    listOf(
                        "AGENTIC",
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
    }
}
