package com.frn.frnstore.feature.product

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.frn.frnstore.FrnViewModel
import com.frn.frnstore.common.EXTRA_KEY_DATA
import com.frn.frnstore.common.FrnSingleObserver
import com.frn.frnstore.common.asyncNetworkRequest
import com.frn.frnstore.data.Comment
import com.frn.frnstore.data.Product
import com.frn.frnstore.data.repo.CommentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ProductDetailsViewModel(bundle: Bundle, commentRepository: CommentRepository) :
    FrnViewModel() {

    val productLiveData = MutableLiveData<Product>()
    val commentsLiveData = MutableLiveData<List<Comment>>()

    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)

        _progressBarLiveData.value = true

        commentRepository.getAll(productLiveData.value!!.id)
            .asyncNetworkRequest()
            .doFinally {
                _progressBarLiveData.value = false
            }
            .subscribe(object : FrnSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentsLiveData.value = t
                }
            })
    }
}