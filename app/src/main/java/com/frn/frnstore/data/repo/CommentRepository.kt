package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Comment
import io.reactivex.Single

interface CommentRepository {

    fun getAll(productId: Int): Single<List<Comment>>

    fun insert(): Single<Comment>
}