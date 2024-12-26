package com.modular.auth.config

import com.modular.auth.domain.service.TokenFilter
import com.modular.auth.domain.service.type.AuthType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenFilter: TokenFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .cors { corsConfigurer ->
                corsConfigurer.configurationSource {
                    CorsConfiguration().apply {
                        this.allowedOrigins = listOf("*")
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
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**"
                    ).permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/members").permitAll()
                    .requestMatchers("/api/v1/members/test").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/members/login").permitAll()
                    .anyRequest().hasAnyAuthority(AuthType.LOGIN.code)
            }
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}