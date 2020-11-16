package com.example.v5baso.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateCatalogResponse(
    @Expose
    @SerializedName("response")
    val response: List<String>,

) {
}