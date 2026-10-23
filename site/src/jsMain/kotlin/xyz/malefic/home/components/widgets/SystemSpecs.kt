package xyz.malefic.home.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.ariaHidden
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ch
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppSpacing
import xyz.malefic.home.styles.AppTypography
import xyz.malefic.home.util.ModuleSize

val SkillSmallSpanStyle =
    SpanStyle(
        1 to 2,
        1 to 2,
        1 to 4,
        1 to 4,
    )

val SkillMediumSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 4,
        2 to 6,
        2 to 8,
    )

val SkillLargeSpanStyle =
    SpanStyle(
        3 to 2,
        3 to 4,
        2 to 8,
        2 to 12,
    )

val EnvSmallSpanStyle =
    SpanStyle(
        1 to 2,
        1 to 2,
        1 to 4,
        1 to 4,
    )

val EnvMediumSpanStyle =
    SpanStyle(
        2 to 2,
        2 to 2,
        1 to 4,
        1 to 4,
    )

val EnvLargeSpanStyle =
    SpanStyle(
        3 to 2,
        3 to 4,
        2 to 8,
        2 to 6,
    )

/** How at home I am in a language, shown as a 4-segment meter plus a word, instead of a meaningless percentage. */
enum class Fluency(
    val label: String,
    val segments: Int,
) {
    DAILY("daily", 4),
    FLUENT("fluent", 3),
    COMFORTABLE("comfortable", 2),
    LEARNING("learning", 1),
}

const val FLUENCY_SEGMENTS = 4

val FluencySegmentStyle =
    CssStyle.base {
        Modifier
            .width(0.625.cssRem)
            .height(0.375.cssRem)
            .backgroundColor(AppColors.static.surfaceVariant.variable)
    }

val FilledFluencySegmentStyle =
    FluencySegmentStyle.extendedByBase {
        Modifier.backgroundColor(AppColors.static.primary.variable)
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
    skills: List<Pair<String, Fluency>>,
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
            skills.forEach { (name, fluency) ->
                Row(Modifier.fillMaxWidth().gap(12.px), verticalAlignment = Alignment.CenterVertically) {
                    SpanText(name.uppercase(), AppTypography.codeSm.weight(1f))
                    // The word carries the meaning; the meter is a glanceable echo of it
                    Row(Modifier.gap(2.px).ariaHidden()) {
                        repeat(FLUENCY_SEGMENTS) { i ->
                            Box((if (i < fluency.segments) FilledFluencySegmentStyle else FluencySegmentStyle).toModifier())
                        }
                    }
                    SpanText(
                        fluency.label,
                        AppTypography.codeSm
                            .color(AppColors.static.onSurfaceVariant.current)
                            .width(11.ch),
                    )
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
