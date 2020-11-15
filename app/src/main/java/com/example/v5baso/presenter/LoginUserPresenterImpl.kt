package com.example.v5baso.presenter

import com.example.v5baso.interactor.LoginUserInteractor
import com.example.v5baso.interactor.LoginUserInteractorImpl
import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.model.request.LoginUserRequest
import com.example.v5baso.view.UserView

class LoginUserPresenterImpl(view: UserView?) : LoginUserPresenter {
    private var view : UserView? = view
    private var interactor: LoginUserInteractor? = null

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

    override fun loginUser(request: LoginUserRequest) {
        interactor = LoginUserInteractorImpl(this)
        if (view != null){
            interactor!!.getLoginInteractor(request)
        }
    }

}