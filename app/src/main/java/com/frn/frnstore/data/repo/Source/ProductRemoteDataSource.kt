package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.Product
import com.frn.frnstore.services.http.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class ProductRemoteDataSource(val apiService: ApiService):ProductDataSource {
    override fun getProducts(sort:Int): Single<List<Product>> = apiService.getProducts(sort.toString())

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