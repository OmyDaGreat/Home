package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.px
import org.w3c.dom.events.Event
import xyz.malefic.home.components.widgets.BlinkingCursor
import xyz.malefic.home.components.widgets.TypewriterText
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.Boot
import xyz.malefic.home.util.START_TIME
import xyz.malefic.home.util.toUptimeString
import kotlin.js.Date
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toKotlinInstant

val BootSequenceStyle =
    CssStyle.base {
        Modifier.color(AppColors.static.onBackground.variable)
    }

@Composable
fun BootSequence(onFinished: () -> Unit = {}) {
    val lines =
        remember {
            listOf(
                "OS: Malefic v${Random.nextInt(0..9)}.${Random.nextInt(0..9)}.${Random.nextInt(0..9)}-stable",
                "Kernel: Linux ${Random.nextInt(0..9)}.${Random.nextInt(0..9)}.${Random.nextInt(0..9)}-malefic-x86_64",
                "Shell: Kotlin 2.4.21",
                "WM: Dank Material Shell on Niri",
                "Uptime: ${(Date().toKotlinInstant() - START_TIME).toUptimeString()}",
                "----------------------------------",
                "Boot sequence complete.",
                "Loading user environment: root",
                "Initializing workspace: [1]",
            )
        }
    val reducedMotion = remember { Boot.prefersReducedMotion }

    var currentLineIndex by remember { mutableIntStateOf(if (reducedMotion) lines.lastIndex else 0) }
    var allLinesFinished by remember { mutableStateOf(reducedMotion) }

    DisposableEffect(Unit) {
        val skip: (Event) -> Unit = { onFinished() }
        window.addEventListener("keydown", skip)
        window.addEventListener("pointerdown", skip)
        onDispose {
            window.removeEventListener("keydown", skip)
            window.removeEventListener("pointerdown", skip)
        }
    }

    LaunchedEffect(allLinesFinished) {
        if (allLinesFinished) {
            delay(1.seconds)
            onFinished()
        }
    }

    Column(BootSequenceStyle.toModifier().fillMaxSize().padding(20.px)) {
        for (i in 0..currentLineIndex) {
            if (reducedMotion) {
                SpanText(lines[i], AppTypography.codeMd)
            } else {
                TypewriterText(lines[i]) {
                    if (i == currentLineIndex) {
                        if (currentLineIndex < lines.lastIndex) {
                            currentLineIndex++
                        } else {
                            allLinesFinished = true
                        }
                    }
                }
            }
        }
        BlinkingCursor()
        SpanText("press any key or tap to skip", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current).margin(top = 24.px))
    }
}
