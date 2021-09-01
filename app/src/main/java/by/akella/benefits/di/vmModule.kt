package by.akella.benefits.di

import by.akella.benefits.ui.viewmodels.BenefitsViewModel
import by.akella.benefits.ui.viewmodels.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { BenefitsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}