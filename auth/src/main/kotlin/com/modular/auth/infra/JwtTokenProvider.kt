package com.modular.auth.infra

import com.modular.auth.domain.service.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}") secretKey: String,
    @Value("\${jwt.access-token-validity-in-milliseconds}") private val accessTokenValidityInMilliseconds: Long,
    @Value("\${jwt.refresh-token-validity-in-milliseconds}") private val refreshTokenValidityInMilliseconds: Long
): TokenProvider {
    override fun createAccessToken(authentication: Authentication): String {
        TODO("Not yet implemented")
    }

    override fun createRefreshToken(): String {
        TODO("Not yet implemented")
    }
}