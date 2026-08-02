package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaEthernet
import com.varabyte.kobweb.silk.components.icons.fa.FaPowerOff
import com.varabyte.kobweb.silk.components.icons.fa.FaTerminal
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.TopLevelPages

val NavItemStyle =
    CssStyle.base {
        Modifier
            .margin(leftRight = 4.px)
            .padding(12.px, 20.px)
            .textDecorationLine(TextDecorationLine.None)
            .color(AppColors.static.onSurface.variable)
            .fontSize(16.px)
            .fontWeight(500)
            .transition(Transition.all(0.2.s))
            .whiteSpace(WhiteSpace.NoWrap)
    }

val InactiveNavItemStyle =
    NavItemStyle.extendedBy {
        hover {
            Modifier
                .background(AppColors.static.primaryTranslucent.variable)
                .color(AppColors.static.onPrimary.variable)
        }
    }

val ActiveNavItemStyle =
    NavItemStyle.extendedByBase {
        Modifier
            .background(AppColors.static.primary.variable)
            .color(AppColors.static.onPrimary.variable)
            .fontWeight(600)
    }

val TopNavContainerStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .height(48.px)
            .background(AppColors.static.surface.variable)
            .borderBottom(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .padding(leftRight = 24.px)
    }

val BrandStyle =
    CssStyle.base {
        AppTypography.headlineMd
            .color(AppColors.static.primary.variable)
            .fontWeight(FontWeight.Bold)
            .letterSpacing((-0.05).em)
    }

val TopNavIconStyle =
    CssStyle.base {
        Modifier.color(AppColors.static.primary.variable).fontSize(20.px)
    }

@Composable
fun TopNavBar() {
    val ctx = rememberPageContext()
    val currentRoute = ctx.route.path

    Row(
        TopNavContainerStyle.toModifier(),
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
                        ActiveNavItemStyle.toModifier()
                    } else {
                        InactiveNavItemStyle.toModifier()
                    }.padding(topBottom = 2.px, leftRight = 8.px),
                ) {
                    SpanText(label, AppTypography.codeSm)
                }
            }
        }

        Row(
            Modifier.gap(16.px),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FaTerminal(TopNavIconStyle.toModifier())
            FaEthernet(TopNavIconStyle.toModifier())
            FaPowerOff(TopNavIconStyle.toModifier())
        }
    }
}
