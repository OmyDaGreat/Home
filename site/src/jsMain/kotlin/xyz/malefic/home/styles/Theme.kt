package xyz.malefic.home.styles

import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.setVariable
import com.varabyte.kobweb.compose.ui.modifiers.textTransform
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.vars.color.BackgroundColorVar
import com.varabyte.kobweb.silk.style.vars.color.BorderColorVar
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.style.vars.color.FocusOutlineColorVar
import com.varabyte.kobweb.silk.style.vars.color.PlaceholderColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.cssClass
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.border
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.colors.palette.checkbox
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.focusOutline
import com.varabyte.kobweb.silk.theme.colors.palette.input
import com.varabyte.kobweb.silk.theme.colors.palette.link
import com.varabyte.kobweb.silk.theme.colors.palette.overlay
import com.varabyte.kobweb.silk.theme.colors.palette.placeholder
import com.varabyte.kobweb.silk.theme.colors.palette.switch
import com.varabyte.kobweb.silk.theme.colors.palette.tab
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.colors.palette.tooltip
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import xyz.malefic.kutint.BasePalette
import xyz.malefic.kutint.PaletteDefinition
import xyz.malefic.kutint.color
import xyz.malefic.kutint.rgba

object AppFonts {
    val mono = listOf("JetBrains Mono", "monospace")
    val sans = listOf("Space Grotesk", "system-ui", "sans-serif")
}

object AppTypography {
    val displayLg =
        Modifier
            .fontFamily(AppFonts.mono)
            .fontSize(48.px)
            .fontWeight(700)
            .lineHeight(1.1)
            .letterSpacing((-0.02).em)

    val displayLgMobile =
        Modifier
            .fontFamily(AppFonts.mono)
            .fontSize(32.px)
            .fontWeight(700)
            .lineHeight(1.2)

    val headlineMd =
        Modifier
            .fontFamily(AppFonts.mono)
            .fontSize(24.px)
            .fontWeight(600)
            .lineHeight(1.4)

    val bodyLg =
        Modifier
            .fontFamily(AppFonts.sans)
            .fontSize(18.px)
            .fontWeight(400)
            .lineHeight(1.6)

    val bodyMd =
        Modifier
            .fontFamily(AppFonts.sans)
            .fontSize(16.px)
            .fontWeight(400)
            .lineHeight(1.5)

    val codeMd =
        Modifier
            .fontFamily(AppFonts.mono)
            .fontSize(16.px)
            .fontWeight(400)
            .lineHeight(1.5)

    val codeSm =
        Modifier
            .fontFamily(AppFonts.mono)
            .fontSize(14.px)
            .fontWeight(400)
            .lineHeight(1.4)

    val labelCaps =
        Modifier
            .fontFamily(AppFonts.mono)
            .fontSize(12.px)
            .fontWeight(700)
            .lineHeight(1.0)
            .letterSpacing(0.1.em)
            .textTransform(TextTransform.Uppercase)
}

object AppSpacing {
    val gapOuter = 24.px
    val gapInner = 16.px
    val containerPadding = 32.px
    val stackSm = 8.px
    val stackMd = 16.px
}

class AppPalette : BasePalette() {
    // ---------------------------------------------------------------
    // Base Colors (Defined once with rgba)
    // ---------------------------------------------------------------
    private val _signalViolet = rgba(164, 94, 229) // #A45EE5 (Signal Violet)
    private val _execGreen = rgba(52, 211, 153) // #34D399 (Exec Green)
    private val _faultAmber = rgba(255, 176, 32) // #FFB020 (Fault Amber)
    private val _fogGray = rgba(107, 114, 128) // #6B7280 (Fog Gray)
    private val _paperWhite = rgba(232, 230, 225) // #E8E6E1 (Paper White)

    @Suppress("ktlint:standard:backing-property-naming")
    private val _darkBackground = rgba(16, 20, 24)

    @Suppress("ktlint:standard:backing-property-naming")
    private val _lightBackground = rgba(250, 248, 255)

    @Suppress("ktlint:standard:backing-property-naming")
    private val _white = rgba(255, 255, 255)

    // Public Brand & TUI Accent Color Properties
    val signalViolet by color(_signalViolet)
    val execGreen by color(_execGreen)
    val faultAmber by color(_faultAmber)
    val fogGray by color(_fogGray)
    val paperWhite by color(_paperWhite)

    // ---------------------------------------------------------------
    // Surface & Background Tonal Scale (Derived from base backgrounds)
    // ---------------------------------------------------------------
    val background by color(_lightBackground, _darkBackground)
    val surface by color(background)
    val surfaceDim by color { surface map { it.darken(0.05f) } }
    val surfaceBright by color(
        _lightBackground.darken(0.02f),
        _darkBackground.lighten(0.15f),
    )
    val surfaceContainerLowest by color(
        _lightBackground.lighten(0.05f),
        _darkBackground.darken(0.03f),
    )
    val surfaceContainerLow by color(
        _lightBackground.darken(0.02f),
        _darkBackground.lighten(0.03f),
    )
    val surfaceContainer by color(
        _lightBackground.darken(0.04f),
        _darkBackground.lighten(0.05f),
    )
    val surfaceContainerHigh by color(
        _lightBackground.darken(0.07f),
        _darkBackground.lighten(0.09f),
    )
    val surfaceContainerHighest by color(
        _lightBackground.darken(0.10f),
        _darkBackground.lighten(0.13f),
    )

    val surfaceVariant by color(surfaceContainerHighest)
    val inverseSurface by color { surface map { it.invert() } }
    val inverseOnSurface by color { inverseSurface map { it.invert() } }

    // On-Surface & Text Colors
    val onBackground by color(
        _lightBackground.invert(),
        _paperWhite,
    )
    val onSurface by color(onBackground)
    val onSurfaceVariant by color(
        _paperWhite.shade(0.3f),
        _paperWhite.tint(0.1f).desaturate(0.15f),
    )

    // -------------------------------------------------------------
    // Primary Colors (Derived from _signalViolet)
    // -------------------------------------------------------------
    val primary by color(
        _signalViolet.darken(0.2f),
        _signalViolet.lighten(0.15f),
    )
    val onPrimary by color(
        _white,
        _signalViolet.darken(0.6f),
    )
    val primaryContainer by color(
        _signalViolet.lighten(0.25f),
        _signalViolet.tint(0.1f),
    )
    val onPrimaryContainer by color(
        _white.darken(0.2f),
        _signalViolet.darken(0.7f),
    )
    val inversePrimary by color(
        _signalViolet.invert(),
        _signalViolet.darken(0.3f),
    )
    val surfaceTint by color(primary)

    // -------------------------------------------------------------
    // Secondary Colors (Derived from _execGreen)
    // -------------------------------------------------------------
    val secondary by color(
        _execGreen.darken(0.25f),
        _execGreen.lighten(0.05f),
    )
    val onSecondary by color(
        _white,
        _execGreen.darken(0.7f),
    )
    val secondaryContainer by color(
        _execGreen.lighten(0.2f),
        _execGreen.shade(0.2f),
    )
    val onSecondaryContainer by color(
        _white.darken(0.2f),
        _execGreen.darken(0.6f),
    )

    // -------------------------------------------------------------
    // Tertiary Colors (Derived from _faultAmber)
    // -------------------------------------------------------------
    val tertiary by color(
        _faultAmber.darken(0.25f),
        _faultAmber,
    )
    val onTertiary by color(
        _white,
        _faultAmber.darken(0.7f),
    )
    val tertiaryContainer by color(
        _faultAmber.lighten(0.2f),
        _faultAmber.shade(0.2f),
    )
    val onTertiaryContainer by color(
        _white.darken(0.2f),
        _faultAmber.darken(0.6f),
    )

    // -------------------------------------------------------------
    // Error Colors (Derived from _faultAmber using hueRotate)
    // -------------------------------------------------------------
    @Suppress("ktlint:standard:backing-property-naming")
    private val _errorBase = _faultAmber.hueRotate(200)
    val error by color(
        _errorBase,
        _errorBase.lighten(0.1f),
    )
    val onError by color(
        _white,
        _errorBase.darken(0.7f),
    )
    val errorContainer by color(
        _errorBase.lighten(0.3f),
        _errorBase.shade(0.6f),
    )
    val onErrorContainer by color(
        _errorBase.darken(0.4f),
        _errorBase.lighten(0.2f),
    )

    // -------------------------------------------------------------
    // Outline Colors (Derived from _fogGray)
    // -------------------------------------------------------------
    val outline by color(
        _fogGray.darken(0.1f),
        _fogGray.lighten(0.15f),
    )
    val outlineVariant by color(
        _fogGray.lighten(0.2f),
        _fogGray.darken(0.3f),
    )

    // -------------------------------------------------------------
    // Fixed Tokens (Derived using lighten, darken)
    // -------------------------------------------------------------
    val primaryFixed by color { primary map { it.lighten(0.2f) } }
    val primaryFixedDim by color(primary)
    val onPrimaryFixed by color { onPrimary map { it.darken(0.1f) } }
    val onPrimaryFixedVariant by color { primary map { it.darken(0.4f) } }

    val secondaryFixed by color { secondary map { it.lighten(0.15f) } }
    val secondaryFixedDim by color(secondary)
    val onSecondaryFixed by color { onSecondary map { it.darken(0.1f) } }
    val onSecondaryFixedVariant by color { secondary map { it.darken(0.4f) } }

    val tertiaryFixed by color { tertiary map { it.lighten(0.15f) } }
    val tertiaryFixedDim by color(tertiary)
    val onTertiaryFixed by color { onTertiary map { it.darken(0.1f) } }
    val onTertiaryFixedVariant by color { tertiary map { it.darken(0.4f) } }

    // -------------------------------------------------------------
    // Helper Translucent Colors (Derived using withAlpha)
    // -------------------------------------------------------------
    val primaryTranslucent by color { primary map { it.withAlpha(0.5f) } }
    val secondaryTranslucent by color { secondary map { it.withAlpha(0.5f) } }
    val signalVioletTranslucent by color { signalViolet map { it.withAlpha(0.5f) } }
}

object AppColors : PaletteDefinition<AppPalette>(AppPalette())

@InitSilk
fun initColor(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ColorMode.loadFromLocalStorage() ?: ColorMode.systemPreference

    ctx.theme.palettes.light.apply {
        background = AppColors.static.background.light.color
        color = AppColors.static.onBackground.light.color
        border = AppColors.static.outlineVariant.light.color
        focusOutline = AppColors.static.primary.light.color
        placeholder = AppColors.static.onSurfaceVariant.light.color
        overlay = rgba(0, 0, 0, 0.5f).color

        input.set(
            hoveredBorder = AppColors.static.primary.light.color,
            invalidBorder = AppColors.static.error.light.color,
            filled = AppColors.static.surfaceVariant.light.color,
            filledHover =
                AppColors.static.surfaceVariant.light.color
                    .darkened(0.05f),
            filledFocus = AppColors.static.primary.light.color,
        )
        button.set(
            default = AppColors.static.primary.light.color,
            hover =
                AppColors.static.primary.light.color
                    .darkened(0.1f),
            focus = AppColors.static.primary.light.color,
            pressed =
                AppColors.static.primary.light.color
                    .darkened(0.2f),
        )
        checkbox.set(
            background = AppColors.static.primary.light.color,
            hover =
                AppColors.static.primary.light.color
                    .darkened(0.1f),
            color = AppColors.static.onPrimary.light.color,
        )
        switch.set(
            backgroundOn = AppColors.static.primary.light.color,
            backgroundOff = AppColors.static.surfaceVariant.light.color,
            thumb = AppColors.static.onPrimary.light.color,
        )
        tab.set(
            color = AppColors.static.onSurfaceVariant.light.color,
            background = AppColors.static.surface.light.color,
            selectedColor = AppColors.static.primary.light.color,
            hover = AppColors.static.surfaceVariant.light.color,
            pressed =
                AppColors.static.surfaceVariant.light.color
                    .darkened(0.1f),
            disabled = AppColors.static.outlineVariant.light.color,
        )
        tooltip.set(
            background = AppColors.static.inverseSurface.light.color,
            color = AppColors.static.inverseOnSurface.light.color,
        )
        link.set(
            default = AppColors.static.primary.light.color,
            visited =
                AppColors.static.primary.light.color
                    .darkened(0.1f),
        )
    }

    ctx.theme.palettes.dark.apply {
        background = AppColors.static.primary.dark.color
        color = AppColors.static.onBackground.dark.color
        border = AppColors.static.outlineVariant.dark.color
        focusOutline = AppColors.static.primary.dark.color
        placeholder = AppColors.static.onSurfaceVariant.dark.color
        overlay = rgba(0, 0, 0, 0.5f).color

        input.set(
            hoveredBorder = AppColors.static.primary.dark.color,
            invalidBorder = AppColors.static.error.dark.color,
            filled = AppColors.static.surfaceVariant.dark.color,
            filledHover =
                AppColors.static.surfaceVariant.dark.color
                    .darkened(0.05f),
            filledFocus = AppColors.static.primary.dark.color,
        )
        button.set(
            default = AppColors.static.primary.dark.color,
            hover =
                AppColors.static.primary.dark.color
                    .darkened(0.1f),
            focus = AppColors.static.primary.dark.color,
            pressed =
                AppColors.static.primary.dark.color
                    .darkened(0.2f),
        )
        checkbox.set(
            background = AppColors.static.primary.dark.color,
            hover =
                AppColors.static.primary.dark.color
                    .darkened(0.1f),
            color = AppColors.static.onPrimary.dark.color,
        )
        switch.set(
            backgroundOn = AppColors.static.primary.dark.color,
            backgroundOff = AppColors.static.surfaceVariant.dark.color,
            thumb = AppColors.static.onPrimary.dark.color,
        )
        tab.set(
            color = AppColors.static.onSurfaceVariant.dark.color,
            background = AppColors.static.surface.dark.color,
            selectedColor = AppColors.static.primary.dark.color,
            hover = AppColors.static.surfaceVariant.dark.color,
            pressed =
                AppColors.static.surfaceVariant.dark.color
                    .darkened(0.1f),
            disabled = AppColors.static.outlineVariant.dark.color,
        )
        tooltip.set(
            background = AppColors.static.inverseSurface.dark.color,
            color = AppColors.static.inverseOnSurface.dark.color,
        )
        link.set(
            default = AppColors.static.primary.dark.color,
            visited =
                AppColors.static.primary.dark.color
                    .darkened(0.1f),
        )
    }

    ColorMode.entries.forEach { colorMode ->
        ctx.stylesheet.registerStyleBase(".${colorMode.cssClass}") {
            val palette = colorMode.toPalette()
            Modifier
                .setVariable(BackgroundColorVar, palette.background)
                .setVariable(ColorVar, palette.color)
                .setVariable(BorderColorVar, palette.border)
                .setVariable(FocusOutlineColorVar, palette.focusOutline)
                .setVariable(PlaceholderColorVar, palette.placeholder)
        }
    }
}
