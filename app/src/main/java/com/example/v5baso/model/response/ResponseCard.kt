package com.example.v5baso.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseCard(
    @Expose
    @SerializedName("_id")
    var _id: String,
    @Expose
    @SerializedName("type_cards")
    var type_cards: List<DetailCard>,
)