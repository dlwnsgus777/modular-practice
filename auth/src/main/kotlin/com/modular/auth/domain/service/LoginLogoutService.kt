package com.modular.auth.domain.service

import com.modular.auth.domain.service.dto.LoginOutput
import com.modular.auth.domain.service.type.AuthType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class LoginLogoutService(
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val tokenProvider: TokenProvider
) {

    fun login(email: String, password: String): LoginOutput {
        log.info("Login in user with email: $email")
        val authorization = authentication(email, password) ?: throw IllegalArgumentException("Invalid credentials")
        return LoginOutput(
            accessToken = tokenProvider.createAccessToken(authorization),
            refreshToken = tokenProvider.createRefreshToken()
        )
    }

    private fun authentication(
        email: String,
        password: String
    ): Authentication? {
        val authenticationManager = authenticationManagerBuilder.getObject()
        val authentication = UsernamePasswordAuthenticationToken(email, password, setOf(SimpleGrantedAuthority(AuthType.LOGIN.code)))
        return authenticationManager.authenticate(authentication)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}