package com.modular.auth.domain.service

import org.springframework.security.core.Authentication

interface TokenParser {
    fun validAccessToken(accessToken: String): Boolean

    fun validRefreshToken(refreshToken: String): Boolean

    fun extractAuthentication(accessToken: String): Authentication
}