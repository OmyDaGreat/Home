package xyz.malefic.home.util

import kotlin.js.Date
import kotlin.time.Duration
import kotlin.time.Instant

val START_TIME = Instant.fromEpochMilliseconds(Date.UTC(2026, 7, 1, 18, 15).toLong())

private fun Int.pad2() = toString().padStart(2, '0')

fun Duration.toUptimeString(): String =
    toComponents { days, hours, minutes, seconds, _ ->
        "${days}d ${hours.pad2()}:${minutes.pad2()}:${seconds.pad2()}"
    }

fun Double.toFixed2(): String = asDynamic().toFixed(2) as String
