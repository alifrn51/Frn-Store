package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductsRepository {

    fun getProducts(): Single<List<Product>>

    fun getFavoriteProducts(): Single<List<Product>>

    fun addFavoriteProducts(): Completable

    fun deleteFavoriteProduct(): Completable
}