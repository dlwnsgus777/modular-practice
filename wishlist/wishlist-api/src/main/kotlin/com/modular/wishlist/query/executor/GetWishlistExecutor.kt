package com.modular.wishlist.query.executor

import com.modular.wishlist.query.domain.service.WishlistQueryService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class GetWishlistExecutor(
    private val wishlistQueryService: WishlistQueryService,
) {

    fun execute(memberId: Long, page: Int, pageSize: Int): Page<WishlistOutput> {
        return wishlistQueryService.getWishlist(memberId, page, pageSize)
    }
}


class WishlistOutput(
    val wishlistId: Long,
    val productId: Long,
    val productName: String
)