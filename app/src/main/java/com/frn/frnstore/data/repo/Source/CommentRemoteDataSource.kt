package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Comment
import com.frn.frnstore.services.http.ApiService
import io.reactivex.Single

class CommentRemoteDataSource(val apiService: ApiService):CommentDataSource {

    override fun getAll(productId: Int): Single<List<Comment>> = apiService.getComments(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }

}