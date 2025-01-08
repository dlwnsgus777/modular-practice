package com.modular.product.query.domain.repository

import com.modular.product.query.domain.ReadOnlyProduct
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReadOnlyProductRepository {
    fun findAll(pageable: Pageable): Page<ReadOnlyProduct>
}