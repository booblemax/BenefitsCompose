package by.akella.benefits.di

import androidx.room.Room
import by.akella.benefits.data.datasource.local.db.BenefitsDb
import by.akella.benefits.data.datasource.remote.benefits.BenefitsRemoteApi
import by.akella.benefits.data.datasource.remote.benefits.BenefitsRemoteApiImpl
import by.akella.benefits.data.datasource.remote.users.UsersRemoteApi
import by.akella.benefits.data.datasource.remote.users.UsersRemoteApiImpl
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
}