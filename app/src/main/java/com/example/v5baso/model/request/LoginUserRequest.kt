package com.example.v5baso.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginUserRequest(
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: String,
)