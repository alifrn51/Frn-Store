package com.frn.frnstore.data.repo

import com.frn.frnstore.data.AddToCartResponse
import com.frn.frnstore.data.CartItemCount
import com.frn.frnstore.data.CartResponse
import com.frn.frnstore.data.MessageResponse
import com.frn.frnstore.data.repo.Source.CartDataSource
import io.reactivex.Single

class CartRepositoryIml(
    val cartRemoteDataSource: CartDataSource
) : CartRepository {
    override fun add(productId: Int): Single<AddToCartResponse> = cartRemoteDataSource.add(productId)

    override fun get(): Single<CartResponse> {
        TODO("Not yet implemented")
    }

    override fun remove(cartItemId: Int): Single<MessageResponse> {
        TODO("Not yet implemented")
    }

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> {
        TODO("Not yet implemented")
    }

    override fun getCartItemCounts(cartItemId: Int): Single<CartItemCount> {
        TODO("Not yet implemented")
    }
}