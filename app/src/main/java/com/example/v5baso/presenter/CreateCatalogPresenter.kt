package com.example.v5baso.presenter

import com.example.v5baso.model.request.CreateUserRequest

interface CreateCatalogPresenter {
    fun showResult(result: String?)
    fun invalidOperation()
    fun createCatalog(tokenString: String)
    fun consultCard(tokenString: String)
}