package com.modular.product.query.infra

import com.modular.product.query.domain.ReadOnlyProduct
import com.modular.product.query.domain.repository.ReadOnlyProductRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaReadOnlyProductRepository: JpaRepository<ReadOnlyProduct, Long>, ReadOnlyProductRepository {
}