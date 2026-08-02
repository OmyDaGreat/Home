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
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant
import kotlin.time.toKotlinInstant

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
    var timeStr by remember { mutableStateOf("") }
    var uptime by remember {
        mutableStateOf(
            Date().toKotlinInstant() - Instant.fromEpochMilliseconds(Date.UTC(2026, 7, 1, 18, 15).toLong()),
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Date()
            val h = now.getUTCHours().toString().padStart(2, '0')
            val m = now.getUTCMinutes().toString().padStart(2, '0')
            val s = now.getUTCSeconds().toString().padStart(2, '0')
            timeStr = "$h:$m:$s"
            uptime = now.toKotlinInstant() - Instant.fromEpochMilliseconds(Date.UTC(2026, 7, 1, 18, 15).toLong())
            delay(1.seconds)
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
