package by.akella.benefits.ui

import android.app.Application
import android.content.Context
import by.akella.benefits.di.dataModule
import by.akella.benefits.di.domainModule
import by.akella.benefits.di.vmModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import android.os.UserManager

import android.os.Build

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(dataModule, domainModule, vmModule)
        }

        FirebaseApp.initializeApp(this)
    }
}