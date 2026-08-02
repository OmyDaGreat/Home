package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppSpacing
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.ModuleSize

val SkillSmallSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 1,
        smCol = 1,
        smRow = 1,
        mdCol = 2,
        mdRow = 1,
        lgCol = 2,
        lgRow = 1,
    )

val SkillMediumSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 2,
        smCol = 2,
        smRow = 2,
        mdCol = 3,
        mdRow = 2,
        lgCol = 4,
        lgRow = 2,
    )

val SkillLargeSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 3,
        smCol = 2,
        smRow = 3,
        mdCol = 4,
        mdRow = 2,
        lgCol = 6,
        lgRow = 2,
    )

val EnvSmallSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 1,
        smCol = 1,
        smRow = 1,
        mdCol = 2,
        mdRow = 1,
        lgCol = 2,
        lgRow = 1,
    )

val EnvMediumSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 2,
        smCol = 2,
        smRow = 2,
        mdCol = 2,
        mdRow = 1,
        lgCol = 3,
        lgRow = 1,
    )

val EnvLargeSpanStyle =
    SpanStyle(
        baseCol = 1,
        baseRow = 3,
        smCol = 2,
        smRow = 3,
        mdCol = 4,
        mdRow = 2,
        lgCol = 3,
        lgRow = 2,
    )

val SkillBarContainerStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .height(6.px)
            .background(AppColors.static.surfaceVariant.variable)
    }

val EnvironmentItemStyle =
    CssStyle.base {
        Modifier
            .border(1.px, LineStyle.Solid, AppColors.static.outline.variable)
            .padding(4.px, 8.px)
            .textAlign(TextAlign.Center)
    }

@Composable
fun SkillModule(
    title: String,
    skills: List<Pair<String, Int>>,
    modifier: Modifier = Modifier,
    size: ModuleSize = ModuleSize.MEDIUM,
) {
    val spanModifier =
        when (size) {
            ModuleSize.SMALL -> SkillSmallSpanStyle.toModifier()
            ModuleSize.MEDIUM -> SkillMediumSpanStyle.toModifier()
            ModuleSize.LARGE -> SkillLargeSpanStyle.toModifier()
        }

    TerminalTile(title = title, modifier = spanModifier.then(modifier)) {
        Column(Modifier.padding(AppSpacing.gapOuter).gap(AppSpacing.gapInner).fillMaxSize(), Arrangement.Center) {
            skills.forEach { (name, progress) ->
                Column(Modifier.fillMaxWidth()) {
                    SpanText(name, AppTypography.codeSm)
                    Box(SkillBarContainerStyle.toModifier().margin(top = 4.px)) {
                        Box(
                            Modifier
                                .background(AppColors.static.primary.current)
                                .fillMaxHeight()
                                .width(progress.percent)
                                .zIndex(1),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnvironmentModule(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier,
    size: ModuleSize = ModuleSize.MEDIUM,
) {
    val spanModifier =
        when (size) {
            ModuleSize.SMALL -> EnvSmallSpanStyle.toModifier()
            ModuleSize.MEDIUM -> EnvMediumSpanStyle.toModifier()
            ModuleSize.LARGE -> EnvLargeSpanStyle.toModifier()
        }

    val internalColumns =
        when (size) {
            ModuleSize.SMALL -> numColumns(base = 1)
            ModuleSize.MEDIUM -> numColumns(base = 2)
            ModuleSize.LARGE -> numColumns(base = 3)
        }

    TerminalTile(title = title, modifier = spanModifier.then(modifier)) {
        SimpleGrid(
            internalColumns,
            Modifier.padding(16.px).gap(8.px).fillMaxSize(),
        ) {
            items.forEach { item ->
                Box(EnvironmentItemStyle.toModifier(), contentAlignment = Alignment.Center) {
                    SpanText(item, AppTypography.codeSm.fillMaxWidth())
                }
            }
        }
    }
}
