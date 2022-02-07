package com.frn.frnstore.data

data class CartResponse(
    val cartItems:CartItem ,
    val payable_price: Int,
    val shipping_cost: Int
)
