package com.example.v5baso.presenter

import com.example.v5baso.interactor.CreateCatalogInteractor
import com.example.v5baso.interactor.CreateCatalogInteractorImpl
import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.view.UserView

class CreateCatalogPresenterImpl(view: UserView) : CreateCatalogPresenter {

    private var view: UserView? = view
    private var interactor: CreateCatalogInteractor? = null

    override fun showResult(result: String?) {
        if (view != null){
            view!!.result(result)
        }
    }

    override fun invalidOperation() {
        if (view != null){
            view!!.invalidateOperation()
        }
    }

    override fun createCatalog(tokenString: String) {
        interactor = CreateCatalogInteractorImpl(this)
        if (view != null){
            interactor!!.getCatalogInteractor(tokenString)
        }
    }

    override fun consultCard(tokenString: String) {
        interactor = CreateCatalogInteractorImpl(this)
        if (view != null){
            interactor!!.getCardInteractor(tokenString)
        }
    }

}