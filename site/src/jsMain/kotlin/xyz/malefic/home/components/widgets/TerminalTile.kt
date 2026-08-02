package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography

val TerminalTileStyle =
    CssStyle.base {
        Modifier
            .border(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .background(AppColors.static.surface.variable)
            .transition(Transition.of("border-color", 0.1.s))
    }

val TerminalTileHoverStyle =
    CssStyle {
        hover {
            Modifier.border(1.px, LineStyle.Solid, AppColors.static.signalViolet.variable)
        }
    }

val TerminalHeaderStyle =
    CssStyle.base {
        Modifier
            .padding(topBottom = 4.px, leftRight = 12.px)
            .borderBottom(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .fillMaxWidth()
            .transition(Transition.of("border-bottom-color", 0.1.s))
    }

val TerminalHeaderTitleStyle =
    CssStyle.base {
        AppTypography.labelCaps.color(AppColors.static.onSurfaceVariant.variable)
    }

val TerminalHeaderStatusStyle =
    CssStyle.base {
        AppTypography.labelCaps
    }

@Composable
fun TerminalTile(
    modifier: Modifier = Modifier,
    title: String? = null,
    status: String? = null,
    statusModifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        TerminalTileStyle
            .toModifier()
            .then(TerminalTileHoverStyle.toModifier())
            .fillMaxHeight()
            .then(modifier),
    ) {
        if (title != null || status != null) {
            Row(
                TerminalHeaderStyle.toModifier(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (title != null) {
                    SpanText(title, TerminalHeaderTitleStyle.toModifier())
                }
                Box(Modifier.weight(1f))
                if (status != null) {
                    SpanText(status, TerminalHeaderStatusStyle.toModifier().then(statusModifier))
                }
            }
        }
        Box(Modifier.fillMaxSize()) {
            content()
        }
    }
}
