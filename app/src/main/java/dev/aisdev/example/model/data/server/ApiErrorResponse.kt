package dev.aisdev.example.model.data.server

import com.google.gson.annotations.SerializedName

class ApiErrorResponse {
    @SerializedName("status")
    var error: String = ""
    @SerializedName("message")
    var message: String = ""
}