package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Animation
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
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.StylePropertyValue
import org.jetbrains.compose.web.css.ch
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.dom.Span

// A real terminal cursor snaps on and off rather than fading
val Blink =
    Keyframes {
        0.percent { Modifier.opacity(1) }
        50.percent { Modifier.opacity(0) }
        100.percent { Modifier.opacity(0) }
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
                        duration = 1.s,
                        timingFunction = AnimationTimingFunction.StepEnd,
                        iterationCount = AnimationIterationCount.Infinite,
                    ),
                ).verticalAlign(VerticalAlign.Middle)
                .margin(left = 2.px)
        }
        cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("reduce"))) {
            Modifier.animation(Animation.None)
        }
    }

@Composable
fun BlinkingCursor(modifier: Modifier = Modifier) = Span(BlinkingCursorStyle.toModifier().then(modifier).toAttrs())
