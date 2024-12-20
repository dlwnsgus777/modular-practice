package com.modular.member.command.api.dto

data class MemberLoginRequestV1(
    val email: String,
    val password: String
) {
}