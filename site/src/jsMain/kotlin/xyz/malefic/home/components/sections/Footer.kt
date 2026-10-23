package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexShrink
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.START_TIME
import xyz.malefic.home.util.toFixed2
import xyz.malefic.home.util.toUptimeString
import kotlin.js.Date
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toKotlinInstant

val FooterContainerStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .height(2.cssRem)
            .minHeight(2.cssRem)
            .background(AppColors.static.surfaceContainerHigh.variable)
            .borderTop(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .padding(leftRight = 24.px)
            .gap(24.px)
            .flexShrink(0)
    }

val FooterStatsStyle =
    CssStyle {
        base {
            Modifier.display(DisplayStyle.None).gap(24.px)
        }
        Breakpoint.LG {
            Modifier.display(DisplayStyle.Flex)
        }
    }

/** Exponential moving averages over a fake load sample, like the kernel's 1/5/15 minute averages. */
private fun List<Double>.withSample(sample: Double) =
    zip(listOf(0.5, 0.15, 0.05)) { average, weight -> average + (sample - average) * weight }

@Composable
fun Footer() {
    val initialDate = remember { Date() }
    var timeISO by remember { mutableStateOf(initialDate.toISOString().substring(11, 19)) }
    var uptime by remember { mutableStateOf(initialDate.toKotlinInstant() - START_TIME) }
    var load by remember { mutableStateOf(List(3) { Random.nextDouble(0.2, 0.6) }) }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Date()
            timeISO = now.toISOString().substring(11, 19)
            uptime = now.toKotlinInstant() - START_TIME

            val msToNextSecond = 1000 - (now.getTime() % 1000).toLong()
            delay(msToNextSecond.milliseconds)
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5.seconds)
            load = load.withSample(Random.nextDouble(0.05, 1.2))
        }
    }

    Row(
        FooterContainerStyle.toModifier(),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically,
    ) {
        SpanText("[ SYSTEMS OPERATIONAL ]", AppTypography.codeSm.color(AppColors.static.secondary.current))

        Row(FooterStatsStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
            SpanText("UPTIME: ${uptime.toUptimeString()}", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
            SpanText(
                "LOAD: ${load.joinToString(" / ") { it.toFixed2() }}",
                AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current),
            )
            SpanText("UTC: $timeISO", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
        }

        SpanText("● ONLINE", AppTypography.codeSm.color(AppColors.static.secondary.current))
    }
}
