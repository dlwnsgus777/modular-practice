package com.modular.wishlist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WishlistInfraApplication

fun main(args: Array<String>) {
    runApplication<WishlistInfraApplication>(*args)
}
