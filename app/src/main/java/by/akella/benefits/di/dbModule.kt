package by.akella.benefits.di

import androidx.room.Room
import by.akella.benefits.data.datasource.local.db.BenefitsDb
import by.akella.benefits.data.datasource.remote.RemoteDataApi
import by.akella.benefits.data.datasource.remote.RemoteDataApiImpl
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {

    single<BenefitsDb> {
        Room.databaseBuilder(get(), BenefitsDb::class.java, "benefitsDb").build()
    }

    single { Firebase.database }

    factory<RemoteDataApi> { RemoteDataApiImpl(get()) }

    factory { get<BenefitsDb>().benefitsDao }
}