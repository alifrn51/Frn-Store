package com.frn.frnstore.feature.product.comment

import androidx.lifecycle.MutableLiveData
import com.frn.frnstore.common.FrnViewModel
import com.frn.frnstore.common.FrnSingleObserver
import com.frn.frnstore.common.asyncNetworkRequest
import com.frn.frnstore.data.Comment
import com.frn.frnstore.data.repo.CommentRepository

class CommentsViewModel(productId: Int, commentRepository: CommentRepository) : FrnViewModel() {

    val commentsLiveData = MutableLiveData<List<Comment>>()
    init {
        _progressBarLiveData.value = true
        commentRepository.getAll(productId)
            .asyncNetworkRequest()
            .doFinally { _progressBarLiveData.value = false }
            .subscribe(object :FrnSingleObserver<List<Comment>>(compositeDisposable){
                override fun onSuccess(t: List<Comment>) {
                    commentsLiveData.value = t
                }
            })
    }
}