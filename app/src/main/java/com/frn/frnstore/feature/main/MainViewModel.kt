package com.frn.frnstore.feature.main

import androidx.lifecycle.MutableLiveData
import com.frn.frnstore.FrnViewModel
import com.frn.frnstore.data.Product
import com.frn.frnstore.data.repo.ProductsRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(val productsRepository: ProductsRepository):FrnViewModel() {

    val liveData = MutableLiveData<List<Product>>()
    init {
        productsRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    liveData.value = t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e.message)
                }

            })
    }

}