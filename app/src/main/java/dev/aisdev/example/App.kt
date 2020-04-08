package dev.aisdev.example

import android.app.Application
import androidx.multidex.MultiDexApplication
import dev.aisdev.example.di.Scopes
import dev.aisdev.example.di.modules.AppModule
import dev.aisdev.example.di.modules.ConvertersModule
import dev.aisdev.example.di.modules.DatabaseModule
import dev.aisdev.example.di.modules.ServerModule
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initDi()
        initAppScope()
    }
    private fun initDi() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }
    }

    private fun initAppScope() {
        val appScope = Toothpick.openScope(Scopes.APP_SCOPE)
        appScope.installModules(AppModule(this))
        appScope.installModules(DatabaseModule())
        appScope.installModules(ConvertersModule())

        val serverScope = Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.SERVER_SCOPE)
        serverScope.installModules(ServerModule(BuildConfig.API_URL))
        serverScope.installModules(ServerModule(BuildConfig.API_URL), ConvertersModule())
    }
}