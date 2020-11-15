package com.example.v5baso.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @Expose
    @SerializedName("success")
    val success: String)