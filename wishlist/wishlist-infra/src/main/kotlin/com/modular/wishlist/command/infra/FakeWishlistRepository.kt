package com.modular.wishlist.command.infra

import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import org.springframework.stereotype.Repository

class FakeWishlistRepository: WishlistRepository {
    private val datas = mutableListOf<Wishlist>()

    override fun save(wishlist: Wishlist): Wishlist {
        if (wishlist.id != null) {
            datas.removeIf { it.id == wishlist.id }
        }
        datas.add(wishlist)
        // jpa 엔티티로 연동하면서 주석처리
//        wishlist.id = datas.size.toLong()
        return wishlist
    }

    override fun findById(id: Long): Wishlist? {
        return datas.find { it.id == id }
    }

    override fun findAllByMemberId(memberId: Long): List<Wishlist> {
        return datas.filter { it.memberId == memberId }
    }

}