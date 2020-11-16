package com.example.v5baso.interactor

import android.util.Log
import com.example.v5baso.model.request.CreateCardRequest
import com.example.v5baso.model.response.CardCatalogResponse
import com.example.v5baso.model.response.CreateCatalogResponse
import com.example.v5baso.model.response.CreateUserResponse
import com.example.v5baso.presenter.CreateCatalogPresenter
import com.example.v5baso.service.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CreateCatalogInteractorImpl(createCatalogPresenter: CreateCatalogPresenter) :
    CreateCatalogInteractor {
    private val presenter: CreateCatalogPresenter? = createCatalogPresenter

    override fun getCatalogInteractor(tokenString: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitClient.buildService2()
                .getCatalog(tokenString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    override fun getCardInteractor(tokenString: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitClient.buildService3()
                .getCard(tokenString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponseCard(response) }, { t -> onFailureCard(t) })
        )
    }

    override fun createCardInteractor(request: CreateCardRequest, tokenString: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitClient.buildService4()
                .getCardNew(request,tokenString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponseCardNew(response) }, { t -> onFailureCard(t) })
        )
    }

    private fun onResponseCardNew(response: CreateUserResponse) {
        Log.e("CardNew", response.success)
        presenter!!.showResult(response.toString())
    }
    private fun onResponseCard(response: CardCatalogResponse) {
        Log.e("Card", response.response._id)
        presenter!!.showResult(response.response._id)
    }

    private fun onFailureCard(response: Throwable) {
        Log.e("Bad", response.message.toString())
        presenter!!.invalidOperation()
    }
    private fun onResponse(response: CreateCatalogResponse) {
        Log.e("Response", response.toString())
        presenter!!.showResult(response.toString())
    }

    private fun onFailure(response: Throwable) {
        Log.e("Bad", response.message.toString())
        presenter!!.invalidOperation()
    }
}