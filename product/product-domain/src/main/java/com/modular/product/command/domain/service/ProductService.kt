package com.modular.product.command.domain.service

import com.modular.product.command.domain.repository.ProductRepository
import com.modular.product.command.domain.service.input.ProductSaveInput
import com.modular.product.command.domain.service.output.ProductSaveOutput
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun save(input: ProductSaveInput): ProductSaveOutput {
        val savedProduct = productRepository.save(input.toEntity())
        return ProductSaveOutput.from(savedProduct)
    }
}