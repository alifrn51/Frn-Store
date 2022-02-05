package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Comment
import com.frn.frnstore.data.repo.Source.CommentRemoteDataSource
import io.reactivex.Single

class CommentRepositoryIml(private val commentRemoteDataSource: CommentRemoteDataSource):CommentRepository {

    override fun getAll(productId: Int): Single<List<Comment>> = commentRemoteDataSource.getAll(productId)


    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}