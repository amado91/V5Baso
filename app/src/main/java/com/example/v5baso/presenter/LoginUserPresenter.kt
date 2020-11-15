package com.example.v5baso.presenter

import com.example.v5baso.model.request.LoginUserRequest

interface LoginUserPresenter {
    fun showResult(result: String?)
    fun invalidOperation()
    fun loginUser(request: LoginUserRequest)
}