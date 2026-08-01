package xyz.malefic.home.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.layout.Layout

@Layout
@Composable
fun NoLayout(content: @Composable () -> Unit) = content()
