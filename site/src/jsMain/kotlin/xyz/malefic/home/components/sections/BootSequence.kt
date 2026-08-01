package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
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
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.widgets.BlinkingCursor
import xyz.malefic.home.components.widgets.TypewriterText
import xyz.malefic.home.styles.AppColors
import kotlin.js.Date
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant
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
                "Shell: Kotlin 2.1.0",
                "WM: Dank Material Shell on Niri",
                "Uptime: ${Date().toKotlinInstant() - Instant.fromEpochMilliseconds(Date.UTC(2026, 7, 1, 18, 15).toLong())}",
                "----------------------------------",
                "Boot sequence complete.",
                "Loading user environment: root",
                "Initializing workspace: [1]",
            )
        }

    var currentLineIndex by remember { mutableIntStateOf(0) }
    var allLinesFinished by remember { mutableStateOf(false) }

    LaunchedEffect(allLinesFinished) {
        if (allLinesFinished) {
            delay(.5.seconds)
            onFinished()
        }
    }

    Column(
        BootSequenceStyle
            .toModifier()
            .fillMaxSize()
            .padding(20.px),
    ) {
        for (i in 0..currentLineIndex) {
            if (i < lines.size) {
                TypewriterText(
                    lines[i],
                ) {
                    if (i == currentLineIndex) {
                        if (currentLineIndex < lines.size - 1) {
                            currentLineIndex++
                        } else {
                            allLinesFinished = true
                        }
                    }
                }
            }
        }
        BlinkingCursor()
    }
}
