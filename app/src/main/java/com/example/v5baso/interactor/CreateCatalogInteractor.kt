package com.example.v5baso.interactor

import com.example.v5baso.model.request.CreateCardRequest

interface CreateCatalogInteractor {
    fun getCatalogInteractor(tokenString: String)
    fun getCardInteractor(tokenString: String)
    fun createCardInteractor(request: CreateCardRequest,tokenString: String)
}