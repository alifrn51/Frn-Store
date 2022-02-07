package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

class ProductLocalDataSource:ProductDataSource {
    override fun getProducts(sort:Int): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun addFavoriteProducts(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFavoriteProduct(): Completable {
        TODO("Not yet implemented")
    }
}