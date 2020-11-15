package com.example.v5baso.service.`interface`

import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.model.request.LoginUserRequest
import com.example.v5baso.model.response.CreateUserResponse
import com.example.v5baso.model.response.LoginUserResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {

    @POST("create")
    fun createUser(@Body user: CreateUserRequest): Observable<CreateUserResponse>

    @POST("authenticate")
    fun loginUser(@Body login: LoginUserRequest): Observable<LoginUserResponse>

}