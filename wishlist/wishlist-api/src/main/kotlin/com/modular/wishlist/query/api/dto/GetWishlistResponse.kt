package com.modular.wishlist.query.api.dto

import com.modular.wishlist.query.executor.WishlistOutput

data class GetWishlistResponse(
    val wishlists: List<WishlistResponse>
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
        fun from(list: List<WishlistOutput>): GetWishlistResponse {
            return GetWishlistResponse(list.map { WishlistResponse.from(it) })
        }
    }
}
