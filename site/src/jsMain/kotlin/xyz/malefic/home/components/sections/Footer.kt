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
import com.varabyte.kobweb.compose.ui.modifiers.bottom
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import kotlin.js.Date
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Instant
import kotlin.time.toKotlinInstant

val START_TIME = Instant.fromEpochMilliseconds(Date.UTC(2026, 7, 1, 18, 15).toLong())

val FooterContainerStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .height(32.px)
            .background(AppColors.static.surfaceContainerHigh.variable)
            .borderTop(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .padding(leftRight = 24.px)
            .position(Position.Fixed)
            .bottom(0.px)
            .zIndex(50)
    }

@Composable
fun Footer() {
    val initialDate = remember { Date() }
    var timeStr by remember { mutableStateOf(initialDate.toISOString().substring(11, 19)) }
    var uptime by remember { mutableStateOf(initialDate.toKotlinInstant() - START_TIME) }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Date()
            timeStr = now.toISOString().substring(11, 19)
            uptime = now.toKotlinInstant() - START_TIME

            val msToNextSecond = 1000 - (now.getTime() % 1000).toLong()
            delay(msToNextSecond.milliseconds)
        }
    }

    Row(
        FooterContainerStyle.toModifier(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SpanText("[ SYSTEM READY ]", AppTypography.codeSm.color(AppColors.static.tertiary.current))

        Row(Modifier.gap(24.px), verticalAlignment = Alignment.CenterVertically) {
            SpanText("UPTIME: $uptime", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
            SpanText("LOAD: 0.14 / 0.11 / 0.08", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
            SpanText("UTC: $timeStr", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
        }

        SpanText("● ONLINE", AppTypography.codeSm.color(AppColors.static.execGreen.current))
    }
}
