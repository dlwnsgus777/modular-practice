package com.modular.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**"
                    ).permitAll()
                    .anyRequest().permitAll()
            }

        return http.build()
    }
}