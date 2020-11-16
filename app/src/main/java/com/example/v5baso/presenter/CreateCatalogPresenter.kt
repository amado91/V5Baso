package com.example.v5baso.presenter

interface CreateCatalogPresenter {
    fun showResult(result: String?)
    fun invalidOperation()
    fun createCatalog(tokenString: String)
    fun consultCard(tokenString: String)
    fun createCard(tokenString: String)
}