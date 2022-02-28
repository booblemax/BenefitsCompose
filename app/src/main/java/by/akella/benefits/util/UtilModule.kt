package by.akella.benefits.util

import org.koin.dsl.module

val utilModule = module {
    factory { CoilInitializer(get()) }
}