package dev.aisdev.example.model.data.server

class ServerError(
    val errorCode: Int,
    val errorResponse: ApiErrorResponse
) : RuntimeException()