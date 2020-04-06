package dev.aisdev.example.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.aisdev.example.BuildConfig
import dev.aisdev.example.model.data.server.UulaApi
import dev.aisdev.example.model.data.server.interceptors.CurlLoggingInterceptor
import dev.aisdev.example.model.data.server.interceptors.ErrorInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "http://kw.uula-staging.com"

@Module
class WebModule(var context: Context) {

    companion object {
        private const val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTA4LCJleH"
        private const val connectionTimeout = 30L
        private const val readTimeout = 30L
    }


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val client = with(OkHttpClient.Builder()){
            addNetworkInterceptor(ErrorInterceptor())
            connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            readTimeout(readTimeout, TimeUnit.SECONDS)

            addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                addNetworkInterceptor(CurlLoggingInterceptor())
            }

            build()
        }


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesLessonsApi(retrofit: Retrofit): UulaApi = retrofit.create(UulaApi::class.java)


}