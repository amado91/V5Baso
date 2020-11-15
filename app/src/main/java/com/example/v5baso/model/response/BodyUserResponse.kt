package com.example.v5baso.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BodyUserResponse(
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("firstname")
    val lastname: String,
    @Expose
    @SerializedName("lastname")
    val password: String,
    @Expose
    @SerializedName("iat")
    val iat: Int,
    @Expose
    @SerializedName("exp")
    val exp: Int,
)