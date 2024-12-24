package com.modular.auth.domain.service.dto


data class LoginOutput(
    val accessToken: String,
    val refreshToken: String
) {
}