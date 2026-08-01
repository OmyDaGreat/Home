package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.VerticalAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.verticalAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.AnimationDirection
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.ch
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Span

val Blink =
    Keyframes {
        from { Modifier.opacity(1) }
        to { Modifier.opacity(0) }
    }

val BlinkingCursorStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.InlineBlock)
                .width(1.ch)
                .height(1.2.em)
                .backgroundColor(Color.currentColor)
                .animation(
                    Blink.toAnimation(
                        duration = 0.5.s,
                        iterationCount = AnimationIterationCount.Infinite,
                        direction = AnimationDirection.Alternate,
                    ),
                ).verticalAlign(VerticalAlign.Middle)
                .margin(left = 2.px)
        }
    }

@Composable
fun BlinkingCursor(modifier: Modifier = Modifier) {
    Span(BlinkingCursorStyle.toModifier().then(modifier).toAttrs())
}
