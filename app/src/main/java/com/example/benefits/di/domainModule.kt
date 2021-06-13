package com.example.benefits.di

import com.example.benefits.data.BenefitsRepositoryImpl
import com.example.benefits.domain.BenefitsRepository
import org.koin.dsl.module

val domainModule = module {

    factory<BenefitsRepository> { BenefitsRepositoryImpl(get()) }
}