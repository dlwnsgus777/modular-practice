package com.modular.wishlist

import com.modular.wishlist.command.domain.Wishlist

interface WishlistRepository {
    fun save(wishlist: Wishlist): Wishlist
}