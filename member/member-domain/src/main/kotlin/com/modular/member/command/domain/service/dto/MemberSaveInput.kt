package com.modular.member.command.domain.service.dto

import com.modular.member.command.domain.Member
import org.springframework.security.crypto.password.PasswordEncoder

data class MemberSaveInput(
    val email: String,
    val password: String
) {
    fun toEntity(encoder: PasswordEncoder): Member {
        return Member(
            email = email,
            password = encoder.encode(password)
        )
    }

}
