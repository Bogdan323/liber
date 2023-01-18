package com.example.liber.domain.usermodel

import com.example.liber.data.Product

data class SavedGames(
    val product: Product,
    val quantity: Int,
) {
    constructor(): this(Product(),1)
}
