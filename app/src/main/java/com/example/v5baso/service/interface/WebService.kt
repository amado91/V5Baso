package com.example.v5baso.service.`interface`

import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.model.request.LoginUserRequest
import com.example.v5baso.model.response.CreateCatalogResponse
import com.example.v5baso.model.response.CreateUserResponse
import com.example.v5baso.model.response.LoginUserResponse
import io.reactivex.Observable
import retrofit2.http.*


interface WebService {

    @POST("create")
    fun createUser(@Body user: CreateUserRequest): Observable<CreateUserResponse>

    @POST("authenticate")
    fun loginUser(@Body login: LoginUserRequest): Observable<LoginUserResponse>

    @GET("accounts")

    fun getCatalog(@Header("x-access-token")token: String): Observable<CreateCatalogResponse>

    @GET("accounts")
    fun fetchPosts(@Header("token") token: String): Observable<CreateCatalogResponse>
}