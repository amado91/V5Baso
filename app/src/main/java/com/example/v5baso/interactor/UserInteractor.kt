package com.example.v5baso.interactor

import com.example.v5baso.model.request.CreateUserRequest

interface UserInteractor {
    fun getUserInteractor(userRequest: CreateUserRequest)
}