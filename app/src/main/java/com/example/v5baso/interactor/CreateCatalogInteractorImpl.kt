package com.example.v5baso.interactor

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

    private fun onResponse(response: String) {
        presenter!!.showResult(response)
    }

    private fun onFailure(response: Any) {
        presenter!!.invalidOperation()
    }
}