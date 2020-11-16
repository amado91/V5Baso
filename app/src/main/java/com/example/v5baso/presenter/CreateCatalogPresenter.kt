package com.example.v5baso.presenter

import com.example.v5baso.model.request.CreateCardRequest

interface CreateCatalogPresenter {
    fun showResult(result: String?)
    fun invalidOperation()
    fun createCatalog(tokenString: String)
    fun consultCard(tokenString: String)
    fun createCard(request: CreateCardRequest, tokenString: String)
}