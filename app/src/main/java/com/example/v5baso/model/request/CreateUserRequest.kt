package com.example.v5baso.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateUserRequest(
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("firstname")
    val firstname: String,
    @Expose
    @SerializedName("lastname")
    val lastname: String,
    @Expose
    @SerializedName("password")
    val password: String,
)