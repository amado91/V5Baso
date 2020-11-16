package com.example.v5baso.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateCardRequest(
    @Expose
    @SerializedName("userId")
    val userId: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("name")
    val name: String,
)