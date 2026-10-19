package xyz.malefic.home.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Animation
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.StylePropertyValue
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import xyz.malefic.home.components.sections.Footer
import xyz.malefic.home.components.sections.Header
import xyz.malefic.home.components.sections.SideNavBar
import xyz.malefic.home.styles.AppColors

val FadeIn =
    Keyframes {
        from { Modifier.opacity(0) }
        to { Modifier.opacity(1) }
    }

val MainLayoutStyle =
    CssStyle {
        base {
            Modifier
                .height(100.vh)
                .fillMaxWidth()
                .overflow(Overflow.Hidden)
                .animation(FadeIn.toAnimation(200.ms, AnimationTimingFunction.EaseOut))
        }
        cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("reduce"))) {
            Modifier.animation(Animation.None)
        }
    }

@Layout
@Composable
fun MainLayout(content: @Composable () -> Unit) {
    Column(MainLayoutStyle.toModifier()) {
        Header()

        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(AppColors.static.background.current)
                .overflow(Overflow.Hidden),
        ) {
            SideNavBar()

            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .overflow(Overflow.Auto),
            ) {
                Box(Modifier.fillMaxWidth().padding(24.px)) {
                    content()
                }
            }
        }

        Footer()
    }
}
