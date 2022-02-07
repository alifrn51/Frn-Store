package com.frn.frnstore.data.repo

import com.frn.frnstore.data.AddToCartResponse
import com.frn.frnstore.data.CartItemCount
import com.frn.frnstore.data.CartResponse
import com.frn.frnstore.data.MessageResponse
import io.reactivex.Single

interface CartRepository {

    fun add(productId: Int): Single<AddToCartResponse>
    fun get(): Single<CartResponse>
    fun remove(cartItemId: Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse>
    fun getCartItemCounts(cartItemId: Int): Single<CartItemCount>

}