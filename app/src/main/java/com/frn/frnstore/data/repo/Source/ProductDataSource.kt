package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {

    fun getProducts(sort:Int): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addFavoriteProducts(): Completable

    fun deleteFavoriteProduct(): Completable


}