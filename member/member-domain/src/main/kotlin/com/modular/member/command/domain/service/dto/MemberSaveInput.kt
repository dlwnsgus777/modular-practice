package com.modular.member.command.domain.service.dto

import com.modular.member.command.domain.Member

data class MemberSaveInput(
    val email: String,
    val password: String
) {
    fun toEntity(): Member {
        return Member(
            email = email,
            password = password
        )
    }

}
