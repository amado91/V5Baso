package com.example.v5baso.presenter

import com.example.v5baso.model.request.CreateUserRequest

interface CreateUserPresenter {
    fun showResult(result: String?)
    fun invalidOperation()
    fun createUser(request: CreateUserRequest)
}