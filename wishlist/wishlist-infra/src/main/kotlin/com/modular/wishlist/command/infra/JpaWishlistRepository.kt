package com.modular.wishlist.command.infra

import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaWishlistRepository: JpaRepository<Wishlist, Long>, WishlistRepository {
}