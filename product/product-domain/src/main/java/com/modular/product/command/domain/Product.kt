package com.modular.product.command.domain

import com.modular.product.command.domain.service.input.ProductUpdateInput
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
class Product(
    @Column(name = "product_name")
    var productName: String,

    @Column(name = "price")
    var price: Int,

    @Column(name = "image_url")
    var imageUrl: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun update(input: ProductUpdateInput) {
        input.productName?.let { productName = it }
        input.price?.let { price = it }
        input.imageUrl?.let { imageUrl = it }
    }
}
