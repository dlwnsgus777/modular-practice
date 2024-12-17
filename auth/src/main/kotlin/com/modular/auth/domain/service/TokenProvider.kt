package com.modular.auth.domain.service

import org.springframework.security.core.Authentication

interface TokenProvider {
    fun createAccessToken(authentication: Authentication): String
    fun createRefreshToken(): String
}