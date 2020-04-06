package dev.aisdev.example.di.providers

import dev.aisdev.example.BuildConfig
import dev.aisdev.example.model.data.server.interceptors.CurlLoggingInterceptor
import dev.aisdev.example.model.data.server.interceptors.ErrorInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class HttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

    companion object {
        private const val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTA4LCJleH"

        private const val connectionTimeout = 30L
        private const val readTimeout = 30L
    }

    override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
        connectTimeout(connectionTimeout, TimeUnit.SECONDS)
        readTimeout(readTimeout, TimeUnit.SECONDS)

        addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return chain.proceed(newRequest)
            }

        })
        addNetworkInterceptor(ErrorInterceptor())

        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addNetworkInterceptor(CurlLoggingInterceptor())
        }

        build()
    }


}
