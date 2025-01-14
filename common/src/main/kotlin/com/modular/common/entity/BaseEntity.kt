package com.modular.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(name = "created_date")
    var createdDate: LocalDateTime = LocalDateTime.now()
        protected set

    @CreatedDate
    @Column(name = "modified_date")
    var modifiedDate: LocalDateTime = LocalDateTime.now()
        protected set
}