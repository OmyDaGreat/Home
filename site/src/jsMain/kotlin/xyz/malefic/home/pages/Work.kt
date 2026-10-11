package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
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
        4 to 1,
        3 to 2,
        2 to 4,
        2 to 6,
    )

val ProjectCategorySpanStyle =
    SpanStyle(
        base = 4 to 1,
        sm = 3 to 2,
        md = 2 to 4,
        lg = 2 to 6,
    )

@Composable
fun Project(
    name: String,
    description: String,
    link: String = "https://github.com/OmyDaGreat/$name/",
    modifier: Modifier = ProjectSpanStyle.toModifier(),
    extra: @Composable () -> Unit = {},
) {
    TerminalTile(
        title = "~/projects/$name",
        status = "[ RUNNING ]",
        modifier = modifier,
    ) {
        Column(Modifier.padding(24.px).gap(16.px)) {
            Link(link, name.uppercase(), AppTypography.headlineMd, variant = AlwaysUnderlinedLinkVariant)
            SpanText(description, AppTypography.bodyMd)
            extra()
        }
    }
}

@Composable
fun ProjectCategory(
    name: String,
    description: String,
    vararg projects: Pair<String, String>,
) {
    TerminalTile(
        title = "~/projects/$name",
        status = "[ RUNNING ]",
        modifier = ProjectCategorySpanStyle.toModifier(),
    ) {
        Column(Modifier.padding(24.px).gap(16.px), horizontalAlignment = Alignment.CenterHorizontally) {
            SpanText(name.uppercase(), AppTypography.headlineMd)
            SpanText(description, AppTypography.bodyMd)
            SimpleGrid(
                numColumns(base = 1, sm = 1, md = 2, lg = 2),
                Modifier.fillMaxWidth().gap(16.px),
            ) {
                projects.forEach { (name, description) ->
                    Project(name, description, modifier = Modifier)
                }
            }
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
            .gap(16.px),
    ) {
        Project(
            "Home",
            "My portfolio and site homepage! Built with Kobweb, this site is a showcase of my work and a hub for anything me-related.",
        )
        Project(
            "KanMan",
            "Another™ Kanban task manager, made with Http4k and Kobweb, supporting alternative clients, a public/private board invitation & role system, and a helpful personal dashboard.",
        ) {
            Link("https://kanman.malefic.xyz/", "Visit the KanMan Demo", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
        }
        Project(
            "Aries",
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
            "Leviathan",
            "A personal assistant that doubles as a pet simulator, allowing you to play games, set reminders, and complete productive tasks with time management tools.",
        )
        Project(
            "GuptaRealEstate",
            "The website for First Team’s Gupta Real Estate, built through Kotlin Multiplatform with a Kobweb frontend and an http4k backend.",
        ) {
            Link("https://guptare.com/", "Visit Gupta Real Estate", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
        }
        Project("404", "A Kobweb 404 page") {
            Link("https://404.malefic.xyz/", "Visit the 404 Page", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
        }
        ProjectCategory(
            "Web Utilities",
            "A collection of useful Kotlin libraries and templates for web development, usually focusing on Kobweb and Http4k.",
            "MaleficStyle" to
                "A styling library for compose based on CSS and DSLs, wrapping modifiers and providing a more idiomatic way to style your components.",
            "MaleficTonic" to "A jekyll theme for Hack Club's Tonic",
            "kutint" to "A Color extension utility library for Kobweb and Compose HTML",
            "StaticSiteTemplate" to "A Kobweb template for a static website",
            "DynamicSiteTemplate" to "A Kobweb + Http4k template for a dynamic website",
        )
        ProjectCategory(
            "WIP",
            "Projects that are still in development and not yet ready for release. Feel free to reach out if you want to pick one up and collaborate with me!",
            "Koven" to "A Kobweb + Http4k opinionated wrapper web framework",
            "Fidget" to "Kotlin Multiplatform Mobile widget system for Android + iOS",
            "SteinsGit" to "A modern git client, designed for ease of use and simplicity.",
            "malefirc" to "An irc-style server and web client setup made with Kobweb.",
            "MalefiKeep" to "A Google Keeps-style notes app for Docker made with Kobweb",
            "Todos" to "A todo app made with Kobweb on the front and backend",
            "mfc" to "A multi-use general purpose terminal application",
            "ArcadeTUI" to "A Kotlin Arcade Machine for the terminal!",
            "Profile" to "A terminal-based git profile switcher made with Kotlin",
            "Doppelganger" to "An SVG wrapper for Kotlin, supporting Compose Multiplatform and Kobweb",
            "iced-kt" to "A GUI library built on Jetpack Compose and the Elm Architecture, based on iced-rs!",
            "Filame" to "A tool to manage arch configuration across multiple devices",
            "MusicPlayer" to "A music player created with Kolin for desktop!",
        )
        ProjectCategory(
            "Desktop Utilities",
            "A collection of useful Kotlin libraries and templates for desktop development, usually focusing on Jetpack Compose for Desktop.",
            "ComposeDesktopTemplate" to
                "A template project for creating desktop applications using Jetpack Compose for Desktop with a focus on simplicity and productivity.",
            "MaleficNavigation" to "A Compose Desktop library for navigation meant to be used alongside PreCompose",
            "MaleficTheming" to "A Compose Desktop library for creating and managing material themes",
            "MaleficComponents" to "A Compose Desktop library containing useful UI elements and components",
            "MaleficPrefs" to "A Compose Desktop library for managing preferences and settings based off of Java's Preferences API",
            "Macalicious" to "A Kotlin/Native Mac project template",
        )
        Project("emKt", "A multiplatform signal library, similar to Godot's signals!")
        Project("MITLicense", "My MIT License hosted online, with cool effects!")
        Project("KotlinScriptTemplate", "A scripting template with built-in scoop support")
        Project("StackManager", "A server Docker stack manager for a very specific setup w/ Tailscale")
        Project("KotlinLibraryTemplate", "A Kotlin/JVM library template with everything you need to get started")
        Project("Mournal", "A music journal frontend for the DailyMalefic server, made with Kobweb") {
            Link("https://mournal.malefic.xyz/", "Visit Mournal", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
        }
        Project(
            "DailyMalefic",
            "A self-hostable journal entry rest server with youtube music integration, acting as a backend for Mournal",
        )
        Project("Kommit", "A conventional commit generator made with Kotlin!")
        Project("MaleficTypes", "A union types framework for Kotlin/JVM")
        Project("MaleficMobile", "Template for Kotlin Multiplatform Mobile applications!")
        Project("MultiLibTemplate", "A Kotlin Multiplatform library template")
        Project("Flute", "A template combining Compose Desktop & Mobile")
        Project("AddItUp", "A Kobweb site for the Add It Up nonprofit")
        Project("sailboat", "An AI-Powered Harvard SEAS Alert System, for The Crimson")
        Project("Malefix", "A Kotlin util library for FRC!")
    }
}
