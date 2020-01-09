package dev.aisdev.example.model.data.server

import com.google.gson.annotations.SerializedName

/**
 * Project MasterKit
 * Package info.superego.masterkit.model.data.server
 *
 * Server response available statuses.
 *
 * Created by Dmitriy Efimov.
 */
enum class ResponseStatus {
    @SerializedName("success")
    SUCCESS,
    @SerializedName("fail")
    FAIL
}