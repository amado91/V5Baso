package com.example.v5baso.presenter

import com.example.v5baso.interactor.UserInteractor
import com.example.v5baso.interactor.UserInteractorImpl
import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.view.UserView

class CreateUserPresenterImpl(view: UserView?): CreateUserPresenter {

    private var view: UserView? = view
    private var interactor: UserInteractor? = null

    override fun showResult(result: String?) {
        if (view != null){
            if (result.equals("Usuario creado exitosamente")){
                view!!.result(result)
            }
        }
    }

    override fun invalidOperation() {
        if (view != null){
            view!!.invalidateOperation()
        }
    }

    override fun createUser(request: CreateUserRequest) {
        interactor = UserInteractorImpl(this)
        if (view != null) {
            interactor!!.getUserInteractor(request)
        }
    }

}