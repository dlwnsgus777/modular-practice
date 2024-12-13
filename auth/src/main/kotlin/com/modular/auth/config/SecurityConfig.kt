package com.modular.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .cors { corsConfigurer ->
                corsConfigurer.configurationSource {
                    CorsConfiguration().apply {
                        this.allowedOrigins = listOf("*") // TODO: need to change
                        this.allowedMethods = listOf("*")
                        this.allowedHeaders = listOf("*")
                        this.allowCredentials = false
                        this.maxAge = 3600L
                    }
                }
            }
            .headers { headerConfigurer -> headerConfigurer.frameOptions { it.sameOrigin() } }
            .logout { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    .anyRequest().permitAll()
            }

        return http.build()
    }
}