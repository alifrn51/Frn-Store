package com.frn.frnstore.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frn.frnstore.FrnViewModel
import com.frn.frnstore.common.FrnSingleObserver
import com.frn.frnstore.common.asyncNetworkRequest
import com.frn.frnstore.data.*
import com.frn.frnstore.data.repo.BannerSliderRepository
import com.frn.frnstore.data.repo.ProductsRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(
    productsRepository: ProductsRepository,
    bannerSliderRepository: BannerSliderRepository) : FrnViewModel() {

    val productLiveData = MutableLiveData<List<Product>>()

    val bannerSliderLiveData = MutableLiveData<List<Banner>>()


    init {

        _progressBarLiveData.value = true

        productsRepository.getProducts(SORT_PRICE_ASC)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _progressBarLiveData.value = false }
            .subscribe(object : FrnSingleObserver<List<Product>>(compositeDisposable){
                override fun onSuccess(t: List<Product>) {
                    productLiveData.value = t
                }

            })

        bannerSliderRepository.getBannerSlider()
            .asyncNetworkRequest()
            .subscribe(object : FrnSingleObserver<List<Banner>>(compositeDisposable){
                override fun onSuccess(t: List<Banner>) {
                    bannerSliderLiveData.value = t
                }

            })


    }

}