package xyz.malefic.home.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.overflowWrap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.AlwaysUnderlinedLinkVariant
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.home.components.widgets.Masonry
import xyz.malefic.home.components.widgets.MasonryItemStyle
import xyz.malefic.home.components.widgets.MasonrySquaredStyle
import xyz.malefic.home.components.widgets.RaisedLinkStyle
import xyz.malefic.home.components.widgets.StretchedLinkStyle
import xyz.malefic.home.components.widgets.TerminalTile
import xyz.malefic.home.styles.AppColors
import xyz.malefic.home.styles.AppTypography

data class ProjectData(
    val name: String,
    val description: String,
    val status: String = "STABLE",
    val extra: @Composable () -> Unit = {},
)

class ProjectCategoryData(
    val name: String,
    val description: String,
    vararg val projects: ProjectData,
)

@Composable
fun ProjectCategory(category: ProjectCategoryData) {
    TerminalTile(
        MasonryItemStyle.toModifier(),
        "~/projects/${category.name.replace(' ', '_')}",
        "${category.projects.size} repos",
    ) {
        Column(Modifier.padding(24.px).gap(16.px)) {
            H2 { Text(category.name.uppercase()) }
            SpanText(category.description, AppTypography.bodyMd.color(AppColors.static.onSurfaceVariant.current))
            Masonry(MasonrySquaredStyle.toModifier()) {
                category.projects.forEach { (pName, pDescription, pStatus, pExtra) ->
                    Project(
                        pName,
                        pDescription,
                        "[$pStatus]",
                    ) { pExtra() }
                }
            }
        }
    }
}

@Composable
fun Project(
    name: String,
    description: String,
    status: String,
    link: String = "https://github.com/OmyDaGreat/$name/",
    extra: @Composable () -> Unit = {},
) = TerminalTile(
    MasonryItemStyle.toModifier(),
    "./$name",
    status,
    interactive = true,
) {
    Column(Modifier.padding(24.px).gap(16.px)) {
        H3(Modifier.overflowWrap(OverflowWrap.Anywhere).toAttrs()) {
            Link(
                link,
                // Zero-width spaces let CamelCase names wrap between words instead of mid-word
                name.replace(Regex("(?<=[a-z0-9])(?=[A-Z])"), "\u200B"),
                StretchedLinkStyle.toModifier(),
                variant = AlwaysUnderlinedLinkVariant,
            )
        }
        SpanText(description, AppTypography.bodyMd)
        Box(RaisedLinkStyle.toModifier()) { extra() }
    }
}

@Page
@Composable
fun WorkPage() {
    val categories =
        listOf(
            ProjectCategoryData(
                "Websites",
                "Sites and web apps I've built with Kobweb and Http4k, from portfolios to freelance work.",
                ProjectData(
                    "Home",
                    "My portfolio and site homepage! Built with Kobweb, this site is a showcase of my work and a hub for anything me-related.",
                ),
                ProjectData("404", "A Kobweb 404 page, acting as the redirect page for any unknown subdomain on malefic.xyz") {
                    Link("https://404.malefic.xyz/", "Visit the 404 Page", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
                },
                ProjectData("Mournal", "A music journal frontend for the DailyMalefic server, made with Kobweb") {
                    Link("https://mournal.malefic.xyz/", "Visit Mournal", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
                },
                ProjectData(
                    "GuptaRealEstate",
                    "The website for First Team’s Gupta Real Estate, built through Kotlin Multiplatform with a Kobweb frontend and an http4k backend.",
                ) {
                    Link("https://guptare.com/", "Visit Gupta Real Estate", AppTypography.codeSm, variant = AlwaysUnderlinedLinkVariant)
                },
                ProjectData("MITLicense", "My MIT License hosted online, with cool effects!"),
                ProjectData("AddItUp", "A Kobweb site for the Add It Up nonprofit"),
            ),
            ProjectCategoryData(
                "Servers",
                "Self-hosted backends and services, built with Http4k and Docker, with Kobweb if bundled with a website.",
                ProjectData(
                    "KanMan",
                    "Another™ Kanban task manager, made with Http4k and Kobweb, supporting alternative clients, a public/private board invitation & role system, and a helpful personal dashboard.",
                ) {
                    Link(
                        "https://kanman.malefic.xyz/",
                        "Visit the KanMan Demo",
                        AppTypography.codeSm,
                        variant = AlwaysUnderlinedLinkVariant,
                    )
                },
                ProjectData("malefirc", "An irc-style server and web client setup made with Kobweb.", "WIP"),
                ProjectData(
                    "DailyMalefic",
                    "A self-hostable journal entry rest server with youtube music integration, acting as a backend for Mournal",
                ),
                ProjectData("StackManager", "A server Docker stack manager for a very specific setup w/ Tailscale"),
            ),
            ProjectCategoryData(
                "Terminal",
                "Command-line and terminal apps, built to be fast and comfortable to use from just the keyboard.",
                ProjectData("sailboat", "A TUI-based AI-powered Harvard SEAS alert system, created for journalists to keep up to date"),
                ProjectData("ArcadeTUI", "A Kotlin Arcade Machine for the terminal, extensible with a wide variety of games!", "WIP"),
                ProjectData(
                    "mfc",
                    "A Kotlin-based terminal application for managing Gradle tasks, RSS feeds, and scheduled jobs via a unified interface.",
                    "WIP",
                ),
                ProjectData("Kommit", "A conventional commit generator made with Kotlin!"),
                ProjectData("KotlinScriptTemplate", "A scripting template with built-in scoop support"),
                ProjectData("Profile", "A terminal-based git profile switcher made with Kotlin", "WIP"),
                ProjectData("Filame", "A tool to manage Arch Linux configuration across multiple devices", "WIP"),
            ),
            ProjectCategoryData(
                "JVM Libraries",
                "Kotlin libraries and starter templates for the JVM, designed to play nice even with a Java environment.",
                ProjectData("MaleficTypes", "A union types framework for Kotlin/JVM"),
                ProjectData("Malefix", "A Kotlin util library for FRC!"),
                ProjectData("KotlinLibraryTemplate", "A Kotlin/JVM library template with everything you need to get started"),
            ),
            ProjectCategoryData(
                "Multiplatform Libraries",
                "Libraries and templates sharing code across most Kotlin Multiplatform targets, focusing on interoperability to the extreme.",
                ProjectData("emKt", "A multiplatform signal library, similar to Godot's signals!"),
                ProjectData("MultiLibTemplate", "A Kotlin Multiplatform library template"),
            ),
            ProjectCategoryData(
                "Desktop & Mobile",
                "Desktop and mobile apps, mostly Kotlin Multiplatform Mobile and Compose for Desktop.",
                ProjectData(
                    "Aries",
                    "Dictation-controlled computer actions, focused on teaching digital literacy to the elderly through controlling your computer.",
                ) {
                    Link(
                        "https://www.congressionalappchallenge.us/24-CA46/",
                        "Congressional App Challenge Winner 2024",
                        AppTypography.codeSm,
                        variant = AlwaysUnderlinedLinkVariant,
                    )
                },
                ProjectData("SteinsGit", "A modern git client, designed for ease of use and simplicity.", "WIP"),
                ProjectData(
                    "Leviathan",
                    "A personal assistant that doubles as a pet simulator, allowing you to play games, set reminders, and complete productive tasks with time management tools.",
                ),
                ProjectData("MusicPlayer", "A music player created with Kolin for desktop!", "WIP"),
            ),
            ProjectCategoryData(
                "Compose Toolkit",
                "Libraries and starter templates for desktop and mobile apps, covering navigation to theming and everything in between.",
                ProjectData(
                    "ComposeDesktopTemplate",
                    "A template project for creating desktop applications using Jetpack Compose for Desktop with a focus on simplicity and productivity.",
                ),
                ProjectData("MaleficMobile", "Template for Kotlin Multiplatform Mobile applications!"),
                ProjectData("Flute", "A template combining Compose Desktop & Mobile"),
                ProjectData("MaleficNavigation", "A Compose Desktop library for navigation meant to be used alongside PreCompose"),
                ProjectData("MaleficTheming", "A Compose Desktop library for creating and managing material themes"),
                ProjectData("MaleficComponents", "A Compose Desktop library containing useful UI elements and components"),
                ProjectData(
                    "MaleficPrefs",
                    "A Compose Desktop library for managing preferences and settings based off of Java's Preferences API",
                ),
                ProjectData("Fidget", "Kotlin Multiplatform Mobile widget system for Android + iOS", "WIP"),
                ProjectData("iced-kt", "A GUI library built on Jetpack Compose and the Elm Architecture, based on iced-rs!", "WIP"),
                ProjectData("Macalicious", "A Kotlin/Native Mac project template, supporting many features specific to macOS development"),
            ),
            ProjectCategoryData(
                "Web Toolkit",
                "Libraries, frameworks, and templates for building on the web, so you can skip the boilerplate.",
                ProjectData("Koven", "A Kobweb + Http4k opinionated wrapper web framework", "WIP"),
                ProjectData("StaticSiteTemplate", "A Kobweb template for a static website"),
                ProjectData("DynamicSiteTemplate", "A Kobweb + Http4k template for a dynamic website"),
                ProjectData("MaleficTonic", "A jekyll theme for Hack Club's Tonic"),
                ProjectData(
                    "MaleficStyle",
                    "A styling library for compose based on CSS and DSLs, wrapping modifiers and providing a more idiomatic way to style your components.",
                ),
                ProjectData("Doppelganger", "An SVG wrapper for Kotlin, supporting Compose Multiplatform and Kobweb", "WIP"),
                ProjectData("kutint", "A Color extension utility library for Kobweb and Compose HTML"),
            ),
        )
    val projectCount = categories.sumOf { it.projects.size }

    Column(Modifier.fillMaxWidth().gap(24.px)) {
        Column(Modifier.gap(8.px)) {
            H1 { Text("PROJECTS") }
            SpanText(
                "$projectCount repositories across ${categories.size} categories",
                AppTypography.codeSm.color(AppColors.static.onSurfaceVariant.current),
            )
        }
        Masonry {
            categories.forEach { ProjectCategory(it) }
        }
    }
}
