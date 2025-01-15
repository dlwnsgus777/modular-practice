package com.modular.product.command.domain.service

import org.springframework.stereotype.Component

@Component
class DefaultProductNamePolicy : ProductNamePolicy {
    override fun validate(name: String) {
        TODO("Not yet implemented")
    }
}