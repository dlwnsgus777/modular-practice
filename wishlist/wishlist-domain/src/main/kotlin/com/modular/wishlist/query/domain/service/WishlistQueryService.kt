package com.modular.wishlist.query.domain.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class WishlistQueryService {
    fun getWishlist(memberId: Long, page: Int, pageSize: Int): Page<com.modular.wishlist.query.executor.WishlistOutput> {
        TODO("Not yet implemented")
    }
}