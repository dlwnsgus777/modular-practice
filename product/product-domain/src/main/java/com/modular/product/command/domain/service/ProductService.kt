package com.modular.product.command.domain.service

import com.modular.product.command.domain.repository.ProductRepository
import com.modular.product.command.domain.service.input.ProductSaveInput
import com.modular.product.command.domain.service.input.ProductUpdateInput
import com.modular.product.command.domain.service.output.ProductSaveOutput
import com.modular.product.command.domain.service.output.ProductUpdateOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(
    private val productRepository: ProductRepository,
    private val productNamePolicy: ProductNamePolicy
) {

    fun save(input: ProductSaveInput): ProductSaveOutput {
        val savedProduct = productRepository.save(input.toEntity())
        return ProductSaveOutput.from(savedProduct)
    }

    fun update(productId: Long, input: ProductUpdateInput): ProductUpdateOutput {
        val product = productRepository.findByIdAndIsDelete(productId, false) ?: throw IllegalArgumentException("상품이 존재하지 않습니다.")
        product.update(input)
        val updatedProduct = productRepository.save(product)
        return ProductUpdateOutput.from(updatedProduct)
    }

    fun delete(productId: Long) {
        val product = productRepository.findByIdAndIsDelete(productId, false) ?: throw IllegalArgumentException("상품이 존재하지 않습니다.")
        product.delete()
    }
}