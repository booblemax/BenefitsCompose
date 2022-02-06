package by.akella.benefits.di

import by.akella.shared.domain.auth.AuthController
import by.akella.shared.domain.auth.AuthControllerImpl
import by.akella.shared.data.repos.BenefitsRepositoryImpl
import by.akella.shared.data.repos.UsersRepositoryImpl
import by.akella.shared.domain.repos.BenefitsRepository
import by.akella.shared.domain.repos.UsersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val domainModule = module {

    factory<BenefitsRepository> { BenefitsRepositoryImpl(get(), get()) }
    factory<UsersRepository> { UsersRepositoryImpl(get(), get()) }
    single<AuthController> { AuthControllerImpl() }
}