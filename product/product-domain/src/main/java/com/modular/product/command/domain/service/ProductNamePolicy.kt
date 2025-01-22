package com.modular.product.command.domain.service

interface ProductNamePolicy {
    fun parsingProductName(name: String): String
}