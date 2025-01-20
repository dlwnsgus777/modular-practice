package com.modular.product.command.domain.service

fun interface ProfanityFilter {
    fun doFilter(productName: String)
}