package xyz.malefic.home.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import xyz.malefic.home.components.sections.Footer
import xyz.malefic.home.components.sections.SideNavBar
import xyz.malefic.home.components.sections.TopNavBar
import xyz.malefic.home.styles.AppColors

@Layout
@Composable
fun MainLayout(content: @Composable () -> Unit) {
    Column(Modifier.height(100.vh).fillMaxWidth().overflow(Overflow.Hidden)) {
        TopNavBar()

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
                    .padding(24.px)
                    .overflow(Overflow.Auto),
            ) {
                content()
            }
        }

        Footer()
    }
}
