package com.modular.auth.domain

import com.modular.common.config.GetMemberId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CurrentMember(
    val id: Long,
    private val email: String,
    private val password: String,
    private val roles: Set<GrantedAuthority>
) : UserDetails, GetMemberId {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.toMutableSet()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun getMemberId(): Long {
        return id
    }
}
