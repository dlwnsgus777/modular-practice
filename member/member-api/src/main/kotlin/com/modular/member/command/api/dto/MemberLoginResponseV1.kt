package com.modular.member.command.api.dto

data class MemberLoginResponseV1(
    val accessToken: String,
    val refreshToken: String?
) {

}
