package com.frn.frnstore.feature.listProduct

import androidx.lifecycle.MutableLiveData
import com.frn.frnstore.common.FrnViewModel
import com.frn.frnstore.R
import com.frn.frnstore.common.FrnSingleObserver
import com.frn.frnstore.common.asyncNetworkRequest
import com.frn.frnstore.data.Product
import com.frn.frnstore.data.repo.ProductsRepository

class ListProductViewModel(
    var sort: Int,
    val productRepository: ProductsRepository
) :
    FrnViewModel() {


    val productLiveData = MutableLiveData<List<Product>>()
    val selectedSortLiveData = MutableLiveData<Int>()

    val sortTitle = arrayOf(
        R.string.sortLasted,
        R.string.sortPopular,
        R.string.sortPriceHeightToLow,
        R.string.sortPriceLowToHeight
    )

    init {
        getProducts()
        selectedSortLiveData.value = sortTitle[sort]
    }

    fun getProducts() {
        _progressBarLiveData.value = true
        productRepository.getProducts(sort)
            .asyncNetworkRequest()
            .doFinally { _progressBarLiveData.value = false }
            .subscribe(object : FrnSingleObserver<List<Product>>(compositeDisposable) {

                override fun onSuccess(t: List<Product>) {
                    productLiveData.value = t
                }

            });
    }


    fun onSelectedSortChangeByUser(sort: Int) {
        this.sort = sort
        this.selectedSortLiveData.value = sortTitle[sort]
        getProducts()

    }

}