package com.modular.product.command.domain.service

interface ProductNamePolicy {
    fun validate(name: String)
}