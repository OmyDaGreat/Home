package xyz.malefic.home.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignSelf
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderRight
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.bottom
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexShrink
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.outlineOffset
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaCircleCheck
import com.varabyte.kobweb.silk.components.icons.fa.FaCodeBranch
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
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.TopLevelPages

val SideNavContainerStyle =
    CssStyle {
        base {
            Modifier
                .display(DisplayStyle.None)
                .width(256.px)
                .minWidth(256.px)
                .flexShrink(0)
                .fillMaxHeight()
                .background(AppColors.static.surfaceContainerLowest.variable)
                .borderRight(1.px, LineStyle.Solid, AppColors.static.outline.variable)
        }
        Breakpoint.LG {
            Modifier.display(DisplayStyle.Flex)
        }
    }

val SideNavHeaderStyle =
    CssStyle.base {
        Modifier
            .padding(16.px)
            .gap(4.px)
            .borderBottom(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .fillMaxWidth()
    }

val SideNavFooterStyle =
    CssStyle.base {
        Modifier
            .padding(16.px)
            .gap(10.px)
            .borderTop(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .fillMaxWidth()
    }

val CommitRowBaseStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .padding(topBottom = 6.px, leftRight = 16.px)
            .gap(10.px)
    }

val CommitRowLinkStyle =
    CommitRowBaseStyle.extendedBy {
        base {
            Modifier
                .display(DisplayStyle.Flex)
                .textDecorationLine(TextDecorationLine.None)
                .transition(Transition.of("background-color", 120.ms))
        }
        hover {
            Modifier.background(AppColors.static.surfaceContainer.variable)
        }
        active {
            Modifier.background(AppColors.static.surfaceContainerHigh.variable).transition(Transition.of("background-color", 0.ms))
        }
        focusVisible {
            Modifier.outline(2.px, LineStyle.Solid, AppColors.static.primary.variable).outlineOffset((-2).px)
        }
    }

val HeadCommitRowStyle =
    CommitRowBaseStyle.extendedByBase {
        Modifier.background(AppColors.static.surfaceContainer.variable)
    }

val GraphGutterStyle =
    CssStyle.base {
        Modifier
            .position(Position.Relative)
            .width(12.px)
            .flexShrink(0)
            .alignSelf(AlignSelf.Stretch)
    }

val GraphLineStyle =
    CssStyle.base {
        Modifier
            .position(Position.Absolute)
            .left(5.px)
            .width(2.px)
            .backgroundColor(AppColors.static.outline.variable)
    }

val CommitNodeStyle =
    CssStyle.base {
        Modifier
            .position(Position.Absolute)
            .left(1.px)
            .top(5.px)
            .size(10.px)
            .borderRadius(50.percent)
            .backgroundColor(AppColors.static.surfaceContainerLowest.variable)
            .border(2.px, LineStyle.Solid, AppColors.static.outline.variable)
    }

val HeadCommitNodeStyle =
    CommitNodeStyle.extendedByBase {
        Modifier
            .backgroundColor(AppColors.static.secondary.variable)
            .border(2.px, LineStyle.Solid, AppColors.static.secondary.variable)
            .boxShadow(spreadRadius = 3.px, color = AppColors.static.secondaryTranslucent.variable)
    }

val CommitMessageStyle =
    CssStyle.base {
        AppTypography.codeSm
            .fontSize(12.px)
            .color(AppColors.static.onSurfaceVariant.variable)
    }

private enum class GraphPosition { FIRST, MIDDLE, LAST }

@Composable
fun SideNavBar() {
    val currentRoute = rememberPageContext().route.path
    val pages = TopLevelPages.entries.reversed()

    Column(SideNavContainerStyle.toModifier()) {
        Column(SideNavHeaderStyle.toModifier()) {
            SpanText("~/dev/portfolio", AppTypography.labelCaps.color(AppColors.static.onSurface.current))
            Row(Modifier.gap(6.px), verticalAlignment = Alignment.CenterVertically) {
                FaCodeBranch(Modifier.fontSize(12.px).color(AppColors.static.secondary.current))
                SpanText("main", AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current))
            }
        }

        Column(Modifier.padding(topBottom = 16.px).weight(1f).fillMaxWidth()) {
            pages.forEachIndexed { index, page ->
                CommitRow(
                    hash = page.hash,
                    subject = page.value.lowercase(),
                    message = page.commitMessage,
                    graphPosition = if (index == 0) GraphPosition.FIRST else GraphPosition.MIDDLE,
                    isHead = page.isCurrentPage(currentRoute),
                    route = page.route,
                )
            }
            CommitRow(
                hash = "e3b0c44",
                subject = "init",
                message = "malefic.xyz",
                graphPosition = GraphPosition.LAST,
                modifier = Modifier.opacity(0.6),
            )
        }

        Row(SideNavFooterStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
            FaCircleCheck(Modifier.fontSize(14.px).color(AppColors.static.secondary.current))
            Column {
                SpanText("On branch main", AppTypography.codeSm.color(AppColors.static.onSurface.current))
                SpanText("up to date with origin/main", CommitMessageStyle.toModifier())
            }
        }
    }
}

/**
 * One commit in the side nav's git graph. A [route] makes it a link (a `git checkout`); the [isHead] commit is the
 * current page and is rendered as a static, highlighted row instead.
 */
@Composable
private fun CommitRow(
    hash: String,
    subject: String,
    message: String,
    graphPosition: GraphPosition,
    isHead: Boolean = false,
    route: String? = null,
    modifier: Modifier = Modifier,
) {
    val content: @Composable () -> Unit = {
        Box(GraphGutterStyle.toModifier()) {
            val line =
                when (graphPosition) {
                    GraphPosition.FIRST -> Modifier.top(10.px).bottom((-6).px)
                    GraphPosition.MIDDLE -> Modifier.top((-6).px).bottom((-6).px)
                    GraphPosition.LAST -> Modifier.top((-6).px).height(16.px)
                }
            Box(GraphLineStyle.toModifier().then(line))
            Box((if (isHead) HeadCommitNodeStyle else CommitNodeStyle).toModifier())
        }
        Column(Modifier.gap(2.px)) {
            Span(AppTypography.codeSm.toAttrs()) {
                SpanText(hash, Modifier.color(AppColors.static.tertiary.current))
                if (isHead) {
                    SpanText(" (", Modifier.color(AppColors.static.onSurfaceVariant.current))
                    SpanText("HEAD -> main", Modifier.color(AppColors.static.secondary.current).fontWeight(FontWeight.Bold))
                    SpanText(")", Modifier.color(AppColors.static.onSurfaceVariant.current))
                }
            }
            Span(CommitMessageStyle.toModifier().toAttrs()) {
                SpanText("$subject: ", Modifier.color(AppColors.static.onSurface.current).fontWeight(FontWeight.Bold))
                SpanText(message)
            }
        }
    }

    when {
        isHead -> {
            Row(HeadCommitRowStyle.toModifier().attr("aria-current", "page").then(modifier)) { content() }
        }

        route != null -> {
            Link(
                route,
                CommitRowLinkStyle.toModifier().then(modifier),
                variant = UndecoratedLinkVariant,
            ) { content() }
        }

        else -> {
            Row(CommitRowBaseStyle.toModifier().then(modifier)) { content() }
        }
    }
}
