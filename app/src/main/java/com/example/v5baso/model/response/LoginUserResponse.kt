package com.example.v5baso.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginUserResponse(
    @Expose
    @SerializedName("token")
    val token: String
) {

}