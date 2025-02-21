package com.modular.wishlist.command.domain

import com.modular.wishlist.WishlistRepository
import org.springframework.stereotype.Repository

@Repository
class FakeWishlistRepository: WishlistRepository {
    private val datas = mutableListOf<Wishlist>()

    override fun save(wishlist: Wishlist): Wishlist {
        if (wishlist.id != null) {
            datas.removeIf { it.id == wishlist.id }
        }
        datas.add(wishlist)
        wishlist.id = datas.size.toLong()
        return wishlist
    }

    override fun findById(id: Long): Wishlist? {
        return datas.find { it.id == id }
    }

}