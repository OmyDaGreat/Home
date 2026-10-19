package xyz.malefic.home.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.isExporting
import kotlinx.browser.sessionStorage
import kotlinx.browser.window

object Boot {
    private const val SESSION_KEY = "malefic-booted"

    var isBooting by mutableStateOf(shouldBootOnLoad())
        private set

    val prefersReducedMotion: Boolean
        get() = window.matchMedia("(prefers-reduced-motion: reduce)").matches

    fun replay() {
        isBooting = true
    }

    fun finish() {
        isBooting = false
        runCatching { sessionStorage.setItem(SESSION_KEY, "1") }
    }

    private fun shouldBootOnLoad(): Boolean =
        !AppGlobals.isExporting &&
            runCatching { sessionStorage.getItem(SESSION_KEY) == null }.getOrDefault(true)
}
