package com.modular.product.command.domain.service

import org.springframework.stereotype.Component

@Component
class DefaultProductNamePolicy(
    private val profanityFilter: ProfanityFilter
) : ProductNamePolicy {

    override fun validate(name: String) {
        if (!ALLOWED_SPECIAL_CHARS.matches(name)) {
            throw IllegalArgumentException("상품명에 허용되지 않는 특수 문자가 포함되어 있습니다.")
        }

        profanityFilter.doFilter(name)

    }

    companion object {
        private val ALLOWED_SPECIAL_CHARS = Regex("^[a-zA-Z0-9가-힣()\\[\\]+\\-&,/_\\s]*\$")
    }
}