package by.akella.shared

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun buildLogger() {
    Napier.base(DebugAntilog())
}