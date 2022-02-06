package by.akella.benefits.ui

import android.app.Application
import by.akella.benefits.di.dataModule
import by.akella.benefits.di.domainModule
import by.akella.benefits.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import by.akella.shared.buildLogger
import by.akella.shared.initFirebase
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(dataModule, domainModule, vmModule)
        }

        initFirebase(this)
        buildLogger()
    }
}