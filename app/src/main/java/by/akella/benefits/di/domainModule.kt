package by.akella.benefits.di

import by.akella.benefits.data.repos.BenefitsRepositoryImpl
import by.akella.benefits.data.repos.UsersRepositoryImpl
import by.akella.benefits.domain.repos.BenefitsRepository
import by.akella.benefits.domain.auth.AuthController
import by.akella.benefits.domain.auth.AuthControllerImpl
import by.akella.benefits.domain.repos.UsersRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val domainModule = module {

    factory<BenefitsRepository> { BenefitsRepositoryImpl(get(), get()) }
    factory<UsersRepository> { UsersRepositoryImpl(get(), get()) }
    single<AuthController> { AuthControllerImpl(FirebaseAuth.getInstance()) }
}