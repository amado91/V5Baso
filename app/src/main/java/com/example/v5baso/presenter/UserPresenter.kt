package com.example.v5baso.presenter

import com.example.v5baso.model.request.CreateUserRequest

interface UserPresenter {
    fun showResult(result: String?)
    fun invalidOperation()
    fun createUser(request: CreateUserRequest)
    fun LogInUser(num1: Int, num2: Int)
}