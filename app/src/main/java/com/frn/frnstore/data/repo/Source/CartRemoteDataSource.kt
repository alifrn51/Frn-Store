package com.frn.frnstore.data.repo.Source

import com.frn.frnstore.data.AddToCartResponse
import com.frn.frnstore.data.CartItemCount
import com.frn.frnstore.data.CartResponse
import com.frn.frnstore.data.MessageResponse
import com.frn.frnstore.services.http.ApiService
import com.google.gson.JsonObject
import io.reactivex.Single

class CartRemoteDataSource(val apiService: ApiService):CartDataSource {
    override fun add(productId: Int): Single<AddToCartResponse> = apiService.addToCart(
        JsonObject().apply {
            addProperty("product_id" , productId)
        }
    )

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