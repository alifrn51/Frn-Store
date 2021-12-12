package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Product
import com.frn.frnstore.data.repo.Source.ProductLocalDataSource
import com.frn.frnstore.data.repo.Source.ProductRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryIml(
    val productRemoteDataSource: ProductRemoteDataSource,
    val productLocalDataSource: ProductLocalDataSource
) : ProductsRepository {
    override fun getProducts(): Single<List<Product>> = productRemoteDataSource.getProducts()

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