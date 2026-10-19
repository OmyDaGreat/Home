package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextOverflow
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.bottom
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.content
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridColumn
import com.varabyte.kobweb.compose.ui.modifiers.gridRow
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.textOverflow
import com.varabyte.kobweb.compose.ui.modifiers.title
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.after
import com.varabyte.kobweb.silk.style.selectors.focusVisible
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography

typealias Row = Int
typealias Col = Int

val Pair<Row, Col>.row
    get() = this.first
val Pair<Row, Col>.col
    get() = this.second

@Suppress("ktlint:standard:function-naming", "FunctionName")
fun SpanStyle(
    base: Pair<Row, Col> = 1 to 1,
    sm: Pair<Row, Col>? = null,
    md: Pair<Row, Col>? = null,
    lg: Pair<Row, Col>? = null,
) = CssStyle {
    base {
        Modifier
            .gridColumn("span ${base.col}")
            .gridRow("span ${base.row}")
    }
    sm?.let { size ->
        Breakpoint.SM {
            Modifier
                .gridColumn("span ${size.col}")
                .gridRow("span ${size.row}")
        }
    }
    md?.let { size ->
        Breakpoint.MD {
            Modifier
                .gridColumn("span ${size.col}")
                .gridRow("span ${size.row}")
        }
    }
    lg?.let { size ->
        Breakpoint.LG {
            Modifier
                .gridColumn("span ${size.col}")
                .gridRow("span ${size.row}")
        }
    }
}

// Long paths would otherwise force their tile wider than its column
val TerminalTitleStyle =
    CssStyle.base {
        Modifier
            .minWidth(0.px)
            .overflow(Overflow.Hidden)
            .textOverflow(TextOverflow.Ellipsis)
            .whiteSpace(WhiteSpace.NoWrap)
            .color(AppColors.static.onSurfaceVariant.variable)
    }

val TerminalTileStyle =
    CssStyle.base {
        Modifier
            .border(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .background(AppColors.static.surface.variable)
    }

/**
 * A tile that is one big click target. Pair it with [StretchedLinkStyle] on the tile's primary link, so the hover and
 * press states only appear on tiles that actually do something.
 */
val InteractiveTerminalTileStyle =
    TerminalTileStyle.extendedBy {
        base {
            Modifier
                .position(Position.Relative)
                .cursor(Cursor.Pointer)
                .transition(Transition.group(listOf("border-color", "background-color"), 120.ms))
        }
        hover {
            Modifier.border(1.px, LineStyle.Solid, AppColors.static.signalViolet.variable)
        }
        // Instant on press: no transition delay between the pointer and the feedback
        active {
            Modifier
                .background(AppColors.static.surfaceContainerLow.variable)
                .transition(Transition.group(listOf("border-color", "background-color"), 0.ms))
        }
        cssRule(":has(:focus-visible)") {
            Modifier.outline(2.px, LineStyle.Solid, AppColors.static.primary.variable)
        }
    }

/** Expands a link's hit area to its nearest positioned ancestor, e.g. an [InteractiveTerminalTileStyle] tile. */
val StretchedLinkStyle =
    CssStyle {
        focusVisible {
            Modifier.outline(0.px)
        }
        after {
            Modifier
                .content("")
                .position(Position.Absolute)
                .top(0.px)
                .right(0.px)
                .bottom(0.px)
                .left(0.px)
        }
    }

/** Lifts secondary links above a [StretchedLinkStyle] link so they stay clickable on their own. */
val RaisedLinkStyle =
    CssStyle.base {
        Modifier.position(Position.Relative).zIndex(1).width(Width.FitContent)
    }

val TerminalHeaderStyle =
    CssStyle.base {
        Modifier
            .minWidth(0.px)
            .gap(8.px)
            .padding(topBottom = 4.px, leftRight = 12.px)
            .borderBottom(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .fillMaxWidth()
    }

@Composable
fun TerminalTile(
    modifier: Modifier = Modifier,
    title: String? = null,
    status: String? = null,
    statusModifier: Modifier = Modifier,
    interactive: Boolean = false,
    content: @Composable () -> Unit,
) {
    val tileStyle = if (interactive) InteractiveTerminalTileStyle else TerminalTileStyle
    Column(tileStyle.toModifier().fillMaxHeight().then(modifier)) {
        if (title != null || status != null) {
            Row(
                TerminalHeaderStyle.toModifier(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (title != null) {
                    SpanText(title, AppTypography.labelCaps.then(TerminalTitleStyle.toModifier()).title(title))
                }
                Box(Modifier.weight(1f))
                if (status != null) {
                    SpanText(status, AppTypography.labelCaps.whiteSpace(WhiteSpace.NoWrap).then(statusModifier))
                }
            }
        }
        Box(Modifier.fillMaxSize()) {
            content()
        }
    }
}
