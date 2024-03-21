package com.gabrielmedeiros.pokeapp

import android.app.Application
import com.gabrielmedeiros.pokeapp.di.pokeAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokeApiApplication)
            modules(pokeAppModules)
        }
    }
}