package com.modular.member.command.api.dto

import com.modular.member.command.domain.service.dto.MemberSaveInput

data class MemberSaveRequestV1(
    val email: String,
    val password: String
) {
    fun toInput(): MemberSaveInput {
        return MemberSaveInput(
            email = email,
            password = password
        )
    }
}