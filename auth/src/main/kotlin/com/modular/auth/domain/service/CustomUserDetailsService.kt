package com.modular.auth.domain.service

import com.modular.auth.domain.CurrentMember
import com.modular.member.command.domain.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found")
        return CurrentMember(member.id!!, member.email, member.password, setOf(SimpleGrantedAuthority("ROLE_USER")))
    }
}