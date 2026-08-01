package xyz.malefic.home.styles

import xyz.malefic.kutint.BasePalette
import xyz.malefic.kutint.PaletteDefinition
import xyz.malefic.kutint.color
import xyz.malefic.kutint.rgba

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

object AppTheme : PaletteDefinition<AppPalette>(AppPalette())
