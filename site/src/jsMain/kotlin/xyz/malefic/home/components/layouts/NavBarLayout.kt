package xyz.malefic.home.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.sections.Footer
import xyz.malefic.home.components.sections.SideNavBar
import xyz.malefic.home.components.sections.TopNavBar
import xyz.malefic.home.styles.AppColors

val MainContentStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxSize()
                .padding(top = 48.px, bottom = 32.px)
                .background(AppColors.static.background.variable)
        }
        Breakpoint.LG {
            Modifier.margin(left = 256.px)
        }
    }

@Layout
@Composable
fun NavBarLayout(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        TopNavBar()
        SideNavBar()

        Column(MainContentStyle.toModifier()) {
            Box(Modifier.fillMaxSize().padding(24.px)) {
                content()
            }
        }

        Footer()
    }
}
