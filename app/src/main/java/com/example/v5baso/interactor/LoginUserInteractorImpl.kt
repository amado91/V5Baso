package com.example.v5baso.interactor

import android.util.Log
import com.auth0.jwt.JWT
import com.example.v5baso.model.request.LoginUserRequest
import com.example.v5baso.model.response.LoginUserResponse
import com.example.v5baso.presenter.LoginUserPresenter
import com.example.v5baso.service.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.apache.commons.codec.binary.Base64

class LoginUserInteractorImpl(loginUserPresenter: LoginUserPresenter) : LoginUserInteractor {
    private val presenter: LoginUserPresenter? = loginUserPresenter

    override fun getLoginInteractor(loginRequest: LoginUserRequest) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitClient.buildService()
                .loginUser(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    private fun onResponse(response: LoginUserResponse) {

        presenter!!.showResult(response.token)
    }

    private fun onFailure(response: Any) {
        Log.e("Error", response.toString())
        presenter!!.invalidOperation()
    }
}