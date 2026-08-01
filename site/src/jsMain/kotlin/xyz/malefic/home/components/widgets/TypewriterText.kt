package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.delay
import xyz.malefic.home.styles.AppTypography
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun TypewriterText(
    text: String,
    speed: Int = 30,
    speedVariance: Int? = 30,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {},
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        displayedText = ""
        text.forEach { char ->
            if (speedVariance != null) {
                delay(Random.nextInt(speed - speedVariance..speed + speedVariance).milliseconds)
            } else {
                delay(speed.milliseconds)
            }
            displayedText += char
        }
        onComplete()
    }

    SpanText(displayedText, AppTypography.codeMd.then(modifier))
}
