package xyz.malefic.home.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
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
                .fillMaxWidth()
                .padding(top = 48.px, bottom = 32.px)
                .background(AppColors.static.background.variable)
        }
    }

@Layout
@Composable
fun MainLayout(content: @Composable () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        TopNavBar()

        Row(MainContentStyle.toModifier()) {
            SideNavBar()

            Box(Modifier.fillMaxSize().padding(24.px)) {
                content()
            }
        }

        Footer()
    }
}
