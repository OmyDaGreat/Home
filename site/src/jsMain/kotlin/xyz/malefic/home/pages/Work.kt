package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridAutoRows
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.AlwaysUnderlinedLinkVariant
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import xyz.malefic.home.components.widgets.SpanStyle
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppTypography

val ProjectSpanStyle =
    SpanStyle(
        1 to 1,
        1 to 2,
        2 to 4,
        2 to 6,
    )

@Composable
fun Project(
    name: String,
    description: String,
    link: String = "https://github.com/OmyDaGreat/$name/",
    extra: @Composable () -> Unit = {},
) {
    TerminalTile(
        title = "~/projects/$name",
        status = "[ RUNNING ]",
        modifier = ProjectSpanStyle.toModifier(),
    ) {
        Column(Modifier.padding(24.px).gap(16.px)) {
            Link(link, name.uppercase(), AppTypography.headlineMd, variant = AlwaysUnderlinedLinkVariant)
            SpanText(description, AppTypography.bodyMd)
            extra()
        }
    }
}

@Page
@Composable
fun WorkPage() {
    SimpleGrid(
        numColumns(base = 2, sm = 4, md = 8, lg = 12),
        Modifier
            .fillMaxWidth()
            .gap(16.px)
            .gridAutoRows { size(120.px) },
    ) {
        Project(
            "home",
            "My portfolio and site homepage! Built with Kobweb, this site is a showcase of my work and a hub for anything me-related.",
        )
        Project(
            "kanman",
            "Another™ Kanban task manager, made with Http4k and Kobweb, supporting alternative clients, a public/private board invitation & role system, and a helpful personal dashboard.",
        ) {
            Link("https://kanman.malefic.xyz/", "Visit the KanMan Demo", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
        }
        Project(
            "aries",
            "Dictation-controlled computer actions, focused on teaching digital literacy to the elderly through controlling your computer.",
        ) {
            Link(
                "https://www.congressionalappchallenge.us/24-CA46/",
                "Congressional App Challenge Winner 2024",
                AppTypography.codeSm,
                variant = AlwaysUnderlinedLinkVariant,
            )
        }
        Project(
            "leviathan",
            "A personal assistant that doubles as a pet simulator, allowing you to play games, set reminders, and complete productive tasks with time management tools.",
        )
    }
}
