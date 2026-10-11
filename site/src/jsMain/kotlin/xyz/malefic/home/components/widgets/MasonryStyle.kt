package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.BreakInside
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.breakInside
import com.varabyte.kobweb.compose.ui.modifiers.columnCount
import com.varabyte.kobweb.compose.ui.modifiers.columnGap
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

val MasonryStyle =
    CssStyle {
        base {
            Modifier.columnCount(1).columnGap(16.px)
        }
        Breakpoint.MD {
            Modifier.columnCount(2)
        }
    }

val MasonrySquaredStyle =
    MasonryStyle.extendedBy {
        Breakpoint.MD {
            Modifier.columnCount(1)
        }
        Breakpoint.XL {
            Modifier.columnCount(2)
        }
    }

val MasonryItemStyle =
    CssStyle.base {
        Modifier.fillMaxWidth().margin(bottom = 16.px).breakInside(BreakInside.Avoid)
    }

@Composable
fun Masonry(
    masonryModifier: Modifier = MasonryStyle.toModifier(),
    extraModifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Div(
        masonryModifier
            .fillMaxWidth()
            .then(extraModifier)
            .toAttrs(),
    ) { content() }
}
