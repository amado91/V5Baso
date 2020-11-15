package com.example.v5baso.interactor

import com.example.v5baso.model.request.CreateUserRequest
import com.example.v5baso.model.response.CreateUserResponse
import com.example.v5baso.presenter.CreateUserPresenter
import com.example.v5baso.service.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserInteractorImpl(userPresenter: CreateUserPresenter) : UserInteractor {

    private val presenter: CreateUserPresenter? = userPresenter

    override fun getUserInteractor(userRequest: CreateUserRequest) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitClient.buildService()
                .createUser(userRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    private fun onResponse(response: CreateUserResponse) {
        presenter!!.showResult(response.success)
    }

    private fun onFailure(response: Any) {

    }
}