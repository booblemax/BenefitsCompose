package by.akella.benefits.di

import by.akella.benefits.data.BenefitsRepositoryImpl
import by.akella.benefits.domain.BenefitsRepository
import org.koin.dsl.module

val domainModule = module {

    factory<BenefitsRepository> { BenefitsRepositoryImpl(get(), get()) }
}