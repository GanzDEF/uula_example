package dev.aisdev.example.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.aisdev.example.model.data.server.UulaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "http://kw.uula-staging.com"

@Module
class WebModule(var context: Context) {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesLessonsApi(retrofit: Retrofit): UulaApi {
        return retrofit.create(UulaApi::class.java)
    }

//    @Provides
//    fun providesLessonsNetworkRepository(): LessonsNetworkRepository {
//        return LessonsNetworkRepository()
//    }


}