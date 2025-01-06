package com.modular.product.command.domain

class Product(
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
    var id: Long? = null
}
