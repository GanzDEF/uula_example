package dev.aisdev.example.model.data.server

import com.google.gson.annotations.SerializedName

class ApiErrorResponse {
    @SerializedName("status")
    var status = ResponseStatus.FAIL
    @SerializedName("message")
    var message: String = ""
}