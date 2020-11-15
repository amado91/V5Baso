package com.example.v5baso.service

import com.example.v5baso.service.`interface`.WebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var BASE_URL = "https://mighty-refuge-81707.herokuapp.com/api/auth/user/"

    private val client = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(WebService::class.java)

    fun buildService(): WebService {
        return retrofit
    }
}