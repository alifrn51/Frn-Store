package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Comment
import io.reactivex.Single

interface CommentDataSource {

    fun getAll(productId: Int): Single<List<Comment>>

    fun insert(): Single<Comment>

}