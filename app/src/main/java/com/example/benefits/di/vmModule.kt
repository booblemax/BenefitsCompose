package com.example.benefits.di

import com.example.benefits.ui.viewmodels.BenefitsViewModel
import com.example.benefits.ui.viewmodels.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { BenefitsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}