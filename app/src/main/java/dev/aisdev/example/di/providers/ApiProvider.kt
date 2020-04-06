package dev.aisdev.example.di.providers

import com.google.gson.Gson
import dev.aisdev.example.di.ServerPath
import dev.aisdev.example.model.data.server.UulaApi
import dev.aisdev.example.model.data.system.SchedulersProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
    private val httpClient: OkHttpClient,
    private val gson: Gson,
    private val schedulersProvider: SchedulersProvider,
    @ServerPath private val serverPath: String
) : Provider<UulaApi> {

    override fun get(): UulaApi {
        return Retrofit.Builder()
            .baseUrl(serverPath)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulersProvider.io()))
            .build()
            .create(UulaApi::class.java)
    }
}
