package by.akella.benefits.di

import by.akella.shared.DriverFactory
import by.akella.shared.createDatabase
import by.akella.shared.data.datasources.BenefitsLocalDataSource
import by.akella.shared.data.datasources.BenefitsRemoteDataSource
import by.akella.shared.data.datasources.UsersLocalDataSource
import by.akella.shared.data.datasources.UsersRemoteDataSource
import by.akella.shared.data.datasources.local.BenefitsLocalDataSourceImpl
import by.akella.shared.data.datasources.local.UsersLocalDataSourceImpl
import by.akella.shared.data.datasources.remote.BenefitsRemoteDataSourceImpl
import by.akella.shared.data.datasources.remote.UsersRemoteDataSourceImpl
import by.akella.sqldelight.benefits.Benefits
import org.koin.dsl.module

val dataModule = module {

//    single<BenefitsDb> {
//        Room.databaseBuilder(get(), BenefitsDb::class.java, "benefitsDb").build()
//    }
//
//    factory<BenefitsRemoteApi> { BenefitsRemoteApiImpl(get()) }
//
//    factory<UsersRemoteApi> { UsersRemoteApiImpl(get()) }
//
//    factory { get<BenefitsDb>().benefitsDao }
//
//    factory { get<BenefitsDb>().usersDao }

//    single { Firebase.database }

    single<Benefits> { createDatabase(DriverFactory(get())) }

    factory<BenefitsRemoteDataSource> { BenefitsRemoteDataSourceImpl() }
    factory<BenefitsLocalDataSource> { BenefitsLocalDataSourceImpl(get()) }

    factory<UsersRemoteDataSource> { UsersRemoteDataSourceImpl() }
    factory<UsersLocalDataSource> { UsersLocalDataSourceImpl(get()) }
}