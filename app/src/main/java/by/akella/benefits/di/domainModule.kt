package by.akella.benefits.di

import by.akella.benefits.data.BenefitsRepositoryImpl
import by.akella.benefits.domain.BenefitsRepository
import by.akella.benefits.domain.auth.AuthController
import by.akella.benefits.domain.auth.AuthControllerImpl
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val domainModule = module {

    factory<BenefitsRepository> { BenefitsRepositoryImpl(get(), get()) }
    single<AuthController> { AuthControllerImpl(FirebaseAuth.getInstance()) }
}