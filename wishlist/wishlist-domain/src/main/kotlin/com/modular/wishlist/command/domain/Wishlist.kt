package com.modular.wishlist.command.domain

class Wishlist(
    var id: Long? = null,
    val memberId: Long,
    val productId: Long
) {

}