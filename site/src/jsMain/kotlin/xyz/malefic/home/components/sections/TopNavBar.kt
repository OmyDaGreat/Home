package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.ariaLabel
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexShrink
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.outlineOffset
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.title
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaPowerOff
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.focusVisible
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.saveToLocalStorage
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.Boot
import xyz.malefic.home.util.TopLevelPages

val NavItemStyle =
    CssStyle {
        base {
            Modifier
                .margin(leftRight = 4.px)
                .padding(topBottom = 2.px, leftRight = 8.px)
                .textDecorationLine(TextDecorationLine.None)
                .color(AppColors.static.onSurface.variable)
                .whiteSpace(WhiteSpace.NoWrap)
                .transition(Transition.group(listOf("background-color", "color"), 120.ms))
        }
        focusVisible {
            Modifier
                .outline(2.px, LineStyle.Solid, AppColors.static.primary.variable)
                .outlineOffset(2.px)
        }
    }

val InactiveNavItemStyle =
    NavItemStyle.extendedBy {
        hover {
            Modifier
                .background(AppColors.static.surfaceContainerHigh.variable)
                .color(AppColors.static.primary.variable)
        }
        // Instant on press: no transition delay between the pointer and the feedback
        active {
            Modifier
                .background(AppColors.static.surfaceContainerHighest.variable)
                .transition(Transition.of("background-color", 0.ms))
        }
    }

val ActiveNavItemStyle =
    NavItemStyle.extendedByBase {
        Modifier
            .background(AppColors.static.primary.variable)
            .color(AppColors.static.onPrimary.variable)
            .fontWeight(600)
    }

val HeaderContainerStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .height(3.cssRem)
            .minHeight(3.cssRem)
            .background(AppColors.static.surface.variable)
            .borderBottom(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .padding(leftRight = 24.px)
            .flexShrink(0)
    }

// Hidden on phones so the nav and header buttons fit; the hero already carries the identity there
val BrandStyle =
    CssStyle {
        base {
            AppTypography.headlineMd
                .display(DisplayStyle.None)
                .color(AppColors.static.primary.variable)
                .fontWeight(FontWeight.Bold)
                .letterSpacing((-0.01).em)
        }
        Breakpoint.MD {
            Modifier.display(DisplayStyle.Block)
        }
    }

val HeaderIconButtonStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.LegacyInlineFlex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .size(2.cssRem)
                .padding(0.px)
                .border(0.px)
                .backgroundColor(Colors.Transparent)
                .color(AppColors.static.primary.variable)
                .fontSize(1.125.cssRem)
                .cursor(Cursor.Pointer)
                .transition(Transition.group(listOf("background-color", "scale"), 120.ms, TransitionTimingFunction.EaseOut))
        }
        hover {
            Modifier.backgroundColor(AppColors.static.surfaceContainerHigh.variable)
        }
        active {
            Modifier
                .backgroundColor(AppColors.static.surfaceContainerHighest.variable)
                .scale(0.92)
                .transition(Transition.group(listOf("background-color", "scale"), 0.ms))
        }
        focusVisible {
            Modifier
                .outline(2.px, LineStyle.Solid, AppColors.static.primary.variable)
                .outlineOffset(2.px)
        }
    }

@Composable
private fun HeaderIconButton(
    label: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    Button(
        HeaderIconButtonStyle
            .toModifier()
            .ariaLabel(label)
            .title(label)
            .onClick { onClick() }
            .toAttrs(),
    ) { icon() }
}

@Composable
fun Header() {
    val ctx = rememberPageContext()
    val currentRoute = ctx.route.path
    var colorMode by ColorMode.currentState

    Row(
        HeaderContainerStyle.toModifier(),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically,
    ) {
        SpanText("ROOT@PORTFOLIO", BrandStyle.toModifier())

        Row(
            Modifier.gap(16.px),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TopLevelPages.entries.forEachIndexed { index, page ->
                val isActive = page.isCurrentPage(currentRoute)
                val label = "[${index + 1} ${page.value.lowercase()}]"

                Link(
                    page.route,
                    if (isActive) {
                        ActiveNavItemStyle.toModifier().attr("aria-current", "page")
                    } else {
                        InactiveNavItemStyle.toModifier()
                    },
                ) {
                    SpanText(label, AppTypography.codeSm)
                }
            }
        }

        Row(
            Modifier.gap(4.px),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HeaderIconButton(
                if (colorMode.isDark) "Switch to light theme" else "Switch to dark theme",
                onClick = {
                    colorMode = colorMode.opposite
                    colorMode.saveToLocalStorage()
                },
            ) {
                if (colorMode.isDark) FaSun() else FaMoon()
            }
            Link(
                "https://github.com/OmyDaGreat",
                HeaderIconButtonStyle.toModifier().ariaLabel("GitHub").title("GitHub"),
                variant = UndecoratedLinkVariant,
            ) { FaGithub() }
            HeaderIconButton("Reboot", onClick = Boot::replay) { FaPowerOff() }
        }
    }
}
