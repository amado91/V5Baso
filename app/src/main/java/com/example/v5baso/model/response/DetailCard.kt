package com.example.v5baso.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailCard(
    @Expose
    @SerializedName("type")
    var type: String,
    @Expose
    @SerializedName("name")
    var name: String,
) {
}