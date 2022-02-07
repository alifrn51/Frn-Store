package com.frn.frnstore.data.repo

import com.frn.frnstore.data.Product
import com.frn.frnstore.data.repo.Source.ProductLocalDataSource
import com.frn.frnstore.data.repo.Source.ProductRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryIml(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductsRepository {
    override fun getProducts(sort: Int): Single<List<Product>> = productRemoteDataSource.getProducts(sort)

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