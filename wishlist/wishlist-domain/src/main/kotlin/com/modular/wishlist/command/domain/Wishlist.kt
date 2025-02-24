package com.modular.wishlist.command.domain

import com.modular.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "wishlist")
class Wishlist(
    @Column(name = "member_id")
    val memberId: Long,

    @Column(name = "product_id")
    val productId: Long
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}