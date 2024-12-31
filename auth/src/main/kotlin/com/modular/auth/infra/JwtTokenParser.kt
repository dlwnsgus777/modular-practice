package com.modular.auth.infra

import com.modular.auth.domain.CurrentMember
import com.modular.auth.domain.service.TokenParser
import com.modular.auth.domain.service.type.AuthType
import com.modular.auth.domain.service.type.TokenType
import com.modular.auth.infra.JwtTokenProvider.Companion.AUTHORITIES_KEY
import com.modular.auth.infra.JwtTokenProvider.Companion.AUTHORITY_DELIMITER
import com.modular.auth.infra.JwtTokenProvider.Companion.MEMBER_ID
import com.modular.auth.infra.JwtTokenProvider.Companion.TOKEN_TYPE_KEY
import com.modular.member.command.domain.repository.MemberRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class JwtTokenParser(
    @Value("\${jwt.secret-key}") private val secretKey: String,
    private val memberRepository: MemberRepository
) : TokenParser {
    private val jwtParser = Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secretKey.toByteArray(Charsets.UTF_8)))
        .build()

    override fun validAccessToken(accessToken: String): Boolean {
        return isValidToken(accessToken, TokenType.ACCESS)
    }

    override fun validRefreshToken(refreshToken: String): Boolean {
        return isValidToken(refreshToken, TokenType.REFRESH)
    }

    override fun extractAuthentication(accessToken: String): Authentication {
        try {
            val claims = jwtParser.parseSignedClaims(accessToken).payload
            return extractAuthentication(claims)
        } catch (e: ExpiredJwtException) {
            return extractAuthentication(e.claims)
        } catch (e: JwtException) {
            throw BadCredentialsException(e.message)
        }
    }

    private fun extractAuthentication(claims: Claims): Authentication {
        val authorities = extractAuthorities(claims) + SimpleGrantedAuthority(AuthType.LOGIN.code)

        val principal = CurrentMember(
            id = claims[MEMBER_ID].toString().toLong(),
            email = claims.subject,
            password = "N/A",
            roles = authorities
        )

        return UsernamePasswordAuthenticationToken(principal, null, authorities)
    }

    private fun extractAuthorities(claims: Claims): Set<GrantedAuthority> {
        return claims[AUTHORITIES_KEY]
            .toString()
            .split(AUTHORITY_DELIMITER)
            .filter { it.isNotBlank() }
            .map { SimpleGrantedAuthority(it) }
            .toSet()
    }

    private fun isValidToken(
        token: String,
        tokenType: TokenType
    ): Boolean {
        try {
            val claims = jwtParser.parseSignedClaims(token).payload
            val extractedType = TokenType.valueOf(claims[TOKEN_TYPE_KEY, String::class.java])

            return extractedType === tokenType
        } catch (e: JwtException) {
            return false
        }
    }
}