package dev.aisdev.example.model.data.server

class ServerError(
    val error: String?,
    override val message: String?
) : RuntimeException()