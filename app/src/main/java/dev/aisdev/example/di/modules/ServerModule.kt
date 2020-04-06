package dev.aisdev.example.di.modules

import com.google.gson.Gson
import dev.aisdev.example.model.data.server.UulaApi
import okhttp3.OkHttpClient
import toothpick.config.Module
import dev.aisdev.example.di.ServerPath
import dev.aisdev.example.di.providers.ApiProvider
import dev.aisdev.example.di.providers.GsonProvider
import dev.aisdev.example.di.providers.HttpClientProvider

class ServerModule(serverPath: String) : Module() {

    init {
        bind(String::class.java).withName(ServerPath::class.java).toInstance(serverPath)
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingleton()
        bind(OkHttpClient::class.java).toProvider(HttpClientProvider::class.java).providesSingleton()
        bind(UulaApi::class.java).toProvider(ApiProvider::class.java).providesSingleton()

    }
}