package com.modular.wishlist.query.api.dto

data class GetWishlistResponse(
    val wishlists: List<WishlistResponse>
) {
    data class WishlistResponse(
        val productId: Int,
        val productName: String
    )
}
