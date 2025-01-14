package com.modular.product.query.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
class ReadOnlyProduct(
    @Id
    var id: Long,
    @Column(name = "product_name")
    val productName: String,

    @Column(name = "price")
    val price: Int,

    @Column(name = "image_url")
    val imageUrl: String,

    @Column(name = "is_delete")
    var isDelete: Boolean
)
