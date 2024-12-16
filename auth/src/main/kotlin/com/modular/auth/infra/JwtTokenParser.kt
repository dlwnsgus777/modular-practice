package com.modular.auth.infra

import com.modular.auth.domain.service.TokenParser
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtTokenParser(
    @Value("\${jwt.secret-key}") private val secretKey: String
): TokenParser {
    override fun validAccessToken(accessToken: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun extractAuthentication(accessToken: String): Authentication {
        TODO("Not yet implemented")
    }

    override fun test(): String {
        return secretKey
    }
}