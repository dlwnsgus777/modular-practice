package com.modular.wishlist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WishlistApiApplication

fun main(args: Array<String>) {
    runApplication<WishlistApiApplication>(*args)
}
