package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor.Companion.Pointer
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderRight
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.icons.fa.FaCode
import com.varabyte.kobweb.silk.components.icons.fa.FaTerminal
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography

val SideNavContainerStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.None)
                .width(256.px)
                .fillMaxHeight()
                .background(AppColors.static.surfaceContainerLowest.variable)
                .borderRight(1.px, LineStyle.Solid, AppColors.static.outline.variable)
        }
        Breakpoint.LG {
            Modifier.display(DisplayStyle.Flex)
        }
    }

val SideNavItemStyle =
    CssStyle {
        base {
            Modifier
                .fillMaxWidth()
                .padding(topBottom = 8.px, leftRight = 16.px)
                .color(AppColors.static.onSecondary.variable)
                .transition(Transition.of("background-color", 0.2.s))
                .cursor(Pointer)
        }
        hover {
            Modifier.background(AppColors.static.secondaryTranslucent.variable)
        }
    }

val ActiveSideNavItemStyle =
    SideNavItemStyle.extendedByBase {
        Modifier
            .background(AppColors.static.secondary.variable)
            .color(AppColors.static.onSecondary.variable)
    }

val SideNavHeaderStyle =
    CssStyle.base {
        Modifier
            .padding(16.px)
            .borderBottom(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .fillMaxWidth()
    }

val SideNavFooterStyle =
    CssStyle.base {
        Modifier
            .padding(16.px)
            .borderTop(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .fillMaxWidth()
    }

val StatusDotStyle =
    CssStyle.base {
        Modifier
            .size(8.px)
            .backgroundColor(AppColors.static.secondary.variable)
            .borderRadius(50.percent)
    }

val StatusTextStyle =
    CssStyle.base {
        AppTypography.codeSm
            .color(AppColors.static.secondary.variable)
            .fontWeight(FontWeight.Bold)
            .margin(left = 8.px)
    }

@Composable
fun SideNavBar() =
    Column(SideNavContainerStyle.toModifier()) {
        Column(SideNavHeaderStyle.toModifier()) {
            SpanText("~/dev/portfolio", AppTypography.labelCaps.color(AppColors.static.onSurface.current))
            SpanText("branch: master", AppTypography.codeSm.opacity(0.5))
        }

        Column(Modifier.padding(topBottom = 16.px).weight(1f).fillMaxWidth()) {
            SideNavItem("[1 main]", true) { FaCode(it) }
            SideNavItem("[2 source]") { FaCode(it) }
            SideNavItem("[3 logs]") { FaTerminal(it) }
            SideNavItem("[4 root]") { FaTerminal(it) }
        }

        Row(
            SideNavFooterStyle.toModifier(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(StatusDotStyle.toModifier())
            SpanText(" SYSTEMS OPERATIONAL", StatusTextStyle.toModifier())
        }
    }

@Composable
private fun SideNavItem(
    label: String,
    isActive: Boolean = false,
    icon: @Composable (Modifier) -> Unit,
) {
    Row(
        (if (isActive) ActiveSideNavItemStyle else SideNavItemStyle).toModifier(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon(Modifier.margin(right = 8.px).fontSize(14.px))
        SpanText(label, AppTypography.codeSm)
    }
}
