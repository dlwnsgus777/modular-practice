package com.modular.product.command.infra

import com.modular.product.command.domain.service.ProfanityFilter
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test


@SpringBootTest
class PurgoMalumProfanityFilterTest {

    @Autowired
    lateinit var purgoMalumProfanityFilter: ProfanityFilter

    @Test
    @DisplayName("비속어가 포함되어 있으면 오류 발생")
//    @Disabled
    fun filter01() {
        // given
        val name = "fuck"

        purgoMalumProfanityFilter.doFilter(name)
    }
}