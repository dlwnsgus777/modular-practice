package com.modular.support

import com.modular.auth.domain.CurrentMember
import com.modular.auth.domain.service.TokenProvider
import com.modular.auth.domain.service.type.AuthType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class SecurityTestSupporter(
    private val tokenProvider: TokenProvider
) {

    fun createTestAccessToken(memberId: Long, email: String): String {
        val authentication = UsernamePasswordAuthenticationToken(
            CurrentMember(memberId, email, "N/A", setOf(SimpleGrantedAuthority(AuthType.LOGIN.code))),
            null,
            listOf(SimpleGrantedAuthority(AuthType.LOGIN.code))
        )

        return tokenProvider.createAccessToken(authentication)
    }
}