package dev.aisdev.example.model.data.server

import com.google.gson.annotations.SerializedName

enum class ResponseStatus {
    @SerializedName("success")
    SUCCESS,
    @SerializedName("fail")
    FAIL
}