package by.akella.benefits.di

import by.akella.benefits.ui.viewmodels.BenefitsViewModel
import by.akella.benefits.ui.viewmodels.CardViewModel
import by.akella.benefits.ui.viewmodels.DetailsViewModel
import by.akella.benefits.ui.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { BenefitsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { CardViewModel(get()) }
}