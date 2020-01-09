package dev.aisdev.example.model.data.server

import dev.aisdev.example.model.data.server.ApiErrorResponse

class ServerError(
    val errorCode: Int,
    val errorResponse: ApiErrorResponse
) : RuntimeException()