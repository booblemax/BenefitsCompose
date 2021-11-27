package by.akella.benefits.di

import androidx.room.Room
import by.akella.benefits.data.datasource.local.db.BenefitsDb
import by.akella.benefits.data.datasource.remote.benefits.BenefitsRemoteApi
import by.akella.benefits.data.datasource.remote.benefits.BenefitsRemoteApiImpl
import by.akella.benefits.data.datasource.remote.users.UsersRemoteApi
import by.akella.benefits.data.datasource.remote.users.UsersRemoteApiImpl
import by.akella.benefits.domain.repos.BenefitsRepository
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {

    single<BenefitsDb> {
        Room.databaseBuilder(get(), BenefitsDb::class.java, "benefitsDb").build()
    }

    single { Firebase.database }

    factory<BenefitsRemoteApi> { BenefitsRemoteApiImpl(get()) }

    factory<UsersRemoteApi> { UsersRemoteApiImpl(get()) }

    factory { get<BenefitsDb>().benefitsDao }

    factory { get<BenefitsDb>().usersDao }

    single<Benefits> { createDatabase(DriverFactory(get())) }

    factory<BenefitsRemoteDataSource> { BenefitsRemoteDataSourceImpl() }
    factory<BenefitsLocalDataSource> { BenefitsLocalDataSourceImpl(get()) }

    factory<UsersRemoteDataSource> { UsersRemoteDataSourceImpl() }
    factory<UsersLocalDataSource> { UsersLocalDataSourceImpl(get()) }
}