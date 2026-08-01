package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.home.components.layouts.NavBarLayout
import xyz.malefic.home.components.sections.BootSequence

@Page
@Layout(".components.layouts.NoLayout")
@Composable
fun HomePage() {
    var isBooting by remember { mutableStateOf(true) }

    if (isBooting) {
        BootSequence { isBooting = false }
    } else {
        NavBarLayout {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text("WELCOME TO MALEFIC PORTFOLIO")
            }
        }
    }
}
