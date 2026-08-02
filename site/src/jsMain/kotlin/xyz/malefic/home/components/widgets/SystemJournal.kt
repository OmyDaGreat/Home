package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.ModuleSize

val JournalSmallSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 2,
        smCol = 1,
        smRow = 2,
        mdCol = 2,
        mdRow = 2,
        lgCol = 3,
        lgRow = 2,
    )

val JournalMediumSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 2,
        smCol = 2,
        smRow = 2,
        mdCol = 4,
        mdRow = 2,
        lgCol = 6,
        lgRow = 2,
    )

val JournalLargeSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 1,
        smCol = 2,
        smRow = 1,
        mdCol = 4,
        mdRow = 1,
        lgCol = 6,
        lgRow = 1,
    )

val JournalLogStyle =
    CssStyle.base {
        Modifier
            .padding(16.px)
            .fillMaxWidth()
            .overflow(Overflow.Auto)
    }

data class LogEntry(
    val timestamp: String,
    val source: String,
    val message: String,
    val color: CSSColorValue? = null,
)

@Composable
fun SystemJournal(
    logs: List<LogEntry>,
    modifier: Modifier = Modifier,
    size: ModuleSize = ModuleSize.MEDIUM,
) {
    val spanModifier =
        when (size) {
            ModuleSize.SMALL -> JournalSmallSpanStyle.toModifier()
            ModuleSize.MEDIUM -> JournalMediumSpanStyle.toModifier()
            ModuleSize.LARGE -> JournalLargeSpanStyle.toModifier()
        }

    TerminalTile(title = "SYSTEM_JOURNAL", status = "LOGS_V3.4", modifier = spanModifier.then(modifier)) {
        Column(JournalLogStyle.toModifier().fillMaxSize()) {
            logs.forEach { log ->
                Row(Modifier.gap(12.px).fillMaxWidth()) {
                    SpanText(
                        "[ ${log.timestamp} ]",
                        AppTypography.codeSm
                            .color(AppColors.static.outline.current)
                            .opacity(0.7)
                            .width(84.px),
                    )
                    SpanText(
                        "${log.source}:",
                        AppTypography.codeSm.fontWeight(FontWeight.Bold).color(log.color ?: AppColors.static.secondary.current),
                    )
                    SpanText(log.message, AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
                }
            }
        }
    }
}
