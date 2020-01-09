package dev.aisdev.example.model.data.server.interceptors

import com.google.gson.Gson
import dev.aisdev.example.model.data.server.ApiErrorResponse
import dev.aisdev.example.model.data.server.ServerError
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code in 400..500) {
            val errorResponse: ApiErrorResponse = if (response.body != null) {
                val body = response.body!!.source().readUtf8()
                Gson().fromJson<ApiErrorResponse>(body,
                    ApiErrorResponse::class.java)
            } else {
                ApiErrorResponse()
            }
            throw ServerError(response.code, errorResponse)
        }
        return response
    }
}