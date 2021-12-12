package com.frn.frnstore.feature.main

import androidx.lifecycle.LiveData
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

class MainViewModel(productsRepository: ProductsRepository):FrnViewModel() {

    val productLiveData = MutableLiveData<List<Product>>()

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData:LiveData<Boolean>
    get() = _progressBarLiveData


    init {

        _progressBarLiveData.value = true

        productsRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally{ _progressBarLiveData.value = false}
            .subscribe(object: SingleObserver<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Product>) {
                    productLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Timber.e(e.message)
                }

            })
    }

}