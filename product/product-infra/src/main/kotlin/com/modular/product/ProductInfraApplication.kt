package com.modular.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductInfraApplication

fun main(args: Array<String>) {
    runApplication<ProductInfraApplication>(*args)
}
