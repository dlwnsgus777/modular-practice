package com.modular.product.command.infra

import com.modular.product.command.domain.Product
import com.modular.product.command.domain.repository.ProductRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaProductRepository: JpaRepository<Product, Long>, ProductRepository {
}