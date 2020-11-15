package com.example.v5baso.interactor

import com.example.v5baso.model.request.LoginUserRequest

interface LoginUserInteractor {
    fun getLoginInteractor(loginRequest: LoginUserRequest)
}