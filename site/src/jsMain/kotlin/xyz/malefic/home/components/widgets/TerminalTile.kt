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
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.gridRow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography

@Suppress("ktlint:standard:function-naming", "FunctionName")
fun SpanStyle(
    baseCol: Int = 1,
    baseRow: Int = 1,
    smCol: Int? = null,
    smRow: Int? = null,
    mdCol: Int? = null,
    mdRow: Int? = null,
    lgCol: Int? = null,
    lgRow: Int? = null,
) = CssStyle {
    base {
        Modifier
            .gridColumn("span $baseCol")
            .gridRow("span $baseRow")
    }
    smCol?.let { col ->
        Breakpoint.SM {
            Modifier
                .gridColumn("span $col")
                .gridRow("span ${smRow ?: baseRow}")
        }
    }
    mdCol?.let { col ->
        Breakpoint.MD {
            Modifier
                .gridColumn("span $col")
                .gridRow("span ${mdRow ?: smRow ?: baseRow}")
        }
    }
    lgCol?.let { col ->
        Breakpoint.LG {
            Modifier
                .gridColumn("span $col")
                .gridRow("span ${lgRow ?: mdRow ?: smRow ?: baseRow}")
        }
    }
}

val TerminalTileStyle =
    CssStyle {
        base {
            Modifier
                .border(1.px, LineStyle.Solid, AppColors.static.outline.variable)
                .background(AppColors.static.surface.variable)
                .transition(Transition.of("border-color", 0.1.s))
        }
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

@Composable
fun TerminalTile(
    modifier: Modifier = Modifier,
    title: String? = null,
    status: String? = null,
    statusModifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(TerminalTileStyle.toModifier().fillMaxHeight().then(modifier)) {
        if (title != null || status != null) {
            Row(
                TerminalHeaderStyle.toModifier(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (title != null) {
                    SpanText(title, AppTypography.labelCaps.color(AppColors.static.onSurfaceVariant.current))
                }
                Box(Modifier.weight(1f))
                if (status != null) {
                    SpanText(status, AppTypography.labelCaps.then(statusModifier))
                }
            }
        }
        Box(Modifier.fillMaxSize()) {
            content()
        }
    }
}
