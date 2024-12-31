package com.modular.auth.infra

import com.modular.auth.domain.CurrentMember
import com.modular.auth.domain.service.TokenProvider
import com.modular.auth.domain.service.type.TokenType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}") secretKey: String,
    @Value("\${jwt.access-token-validity-in-milliseconds}") private val accessTokenValidityInMilliseconds: Long,
    @Value("\${jwt.refresh-token-validity-in-milliseconds}") private val refreshTokenValidityInMilliseconds: Long
) : TokenProvider {
    private val secretKey: Key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    override fun createAccessToken(authentication: Authentication): String {
        val now = Date()
        val validity = Date(now.time + accessTokenValidityInMilliseconds)
        try {
            val member = authentication.principal as CurrentMember

            return Jwts.builder()
                .subject(member.username)
                .claim(AUTHORITIES_KEY, toString(authentication.authorities))
                .claim(TOKEN_TYPE_KEY, TokenType.ACCESS)
                .claim(MEMBER_ID, member.id)
                .signWith(secretKey)
                .issuedAt(now)
                .expiration(validity)
                .compact()
        } catch (e: Exception) {
            throw IllegalStateException("CurrentMember error: ${authentication.principal}", e)
        }
    }

    override fun createRefreshToken(): String {
        val now = Date()
        val validity = Date(now.time + refreshTokenValidityInMilliseconds)

        return Jwts.builder()
            .claim(TOKEN_TYPE_KEY, TokenType.REFRESH)
            .signWith(secretKey)
            .issuedAt(now)
            .expiration(validity)
            .compact()
    }

    private fun toString(authorities: Collection<GrantedAuthority>): String {
        return authorities.joinToString(AUTHORITY_DELIMITER) { it.authority }
    }

    companion object {
        const val AUTHORITIES_KEY = "auth"
        const val AUTHORITY_DELIMITER = ","
        const val TOKEN_TYPE_KEY = "type"
        const val MEMBER_ID = "id"
    }
}