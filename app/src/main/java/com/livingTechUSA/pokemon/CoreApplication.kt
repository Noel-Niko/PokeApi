package com.livingTechUSA.pokemon

import android.app.Application
import com.axxess.palliative.service.koin.module.NetworkModule
import com.facebook.stetho.Stetho
import com.livingTechUSA.pokemon.services.koin.module.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class CoreApplication : Application(), KoinComponent {

    companion object {
        private lateinit var sInstance: CoreApplication

        @JvmStatic
        fun getInstance(): CoreApplication = sInstance

    }


    override fun onCreate() {
        super.onCreate()
        sInstance = this

        initKoin()
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }


    fun initKoin() {
        startKoin {
            logger(PrintLogger(Level.ERROR)) //Required to use navigation components and prevent crash at run time. See https://github.com/InsertKoinIO/koin/issues/1188
            androidContext(this@CoreApplication)
            modules(
                listOf(
                    NetworkModule,
                    DatabaseModule
                )
            )
        }
    }

}