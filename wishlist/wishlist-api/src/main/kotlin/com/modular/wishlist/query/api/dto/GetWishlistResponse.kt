package com.modular.wishlist.query.api.dto

import com.modular.wishlist.query.executor.WishlistOutput
import org.springframework.data.domain.Page

data class GetWishlistResponse(
    val wishlist: List<WishlistResponse>,
    val page: Int,
    val pageSize: Int,
    val totalElements: Long = 0,
) {
    data class WishlistResponse(
        val wishlistId: Long,
        val productId: Long,
        val productName: String
    ) {
        companion object {
            fun from(output: WishlistOutput): WishlistResponse {
                return WishlistResponse(output.wishlistId, output.productId, output.productName)
            }
        }
    }

    companion object {
        fun from(result: Page<WishlistOutput>): GetWishlistResponse {
            return GetWishlistResponse(
                result.content.map { WishlistResponse.from(it) },
                result.pageable.pageNumber,
                result.pageable.pageSize,
                result.totalElements
            )
        }
    }
}
