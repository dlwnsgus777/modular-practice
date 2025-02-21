package com.modular.auth.domain.service

import com.modular.auth.domain.CurrentMember
import com.modular.auth.domain.service.dto.LoginOutput
import com.modular.auth.domain.service.type.AuthType
import com.modular.member.command.domain.Member
import com.modular.member.command.domain.repository.MemberRepository
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
    private val tokenProvider: TokenProvider,
    private val memberRepository: MemberRepository
) {

    fun login(email: String, password: String): LoginOutput {
        val member = memberRepository.findByEmail(email) ?: throw IllegalArgumentException("Invalid credentials")
        log.info("Login in user with email: $email")
        val authorization = authentication(member, password) ?: throw IllegalArgumentException("Invalid credentials")
        return LoginOutput(
            accessToken = tokenProvider.createAccessToken(authorization),
            refreshToken = tokenProvider.createRefreshToken()
        )
    }

    private fun authentication(
        member: Member,
        password: String
    ): Authentication? {
        val authenticationManager = authenticationManagerBuilder.getObject()

        val authorities = setOf(SimpleGrantedAuthority(AuthType.LOGIN.code))
        val principal = CurrentMember(
            id = member.id!!,
            email = member.email,
            password = "N/A",
            roles = authorities
        )
        val authentication = UsernamePasswordAuthenticationToken(principal, password, authorities)
        return authenticationManager.authenticate(authentication)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}