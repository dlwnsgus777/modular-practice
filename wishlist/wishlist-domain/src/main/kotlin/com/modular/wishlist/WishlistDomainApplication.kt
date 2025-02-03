package com.modular.wishlist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WishlistDomainApplication

fun main(args: Array<String>) {
	runApplication<WishlistDomainApplication>(*args)
}
