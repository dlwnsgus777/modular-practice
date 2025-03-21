package com.modular.product.command.domain.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DefaultProductNamePolicyTest {

    lateinit var policy: DefaultProductNamePolicy

    @MockK
    lateinit var filter: ProfanityFilter

    @BeforeEach
    fun setUp() {
        policy = DefaultProductNamePolicy(filter)
    }

    @Test
    @DisplayName("특수 문자는 (),[],+,-,&,/,_ 만 허용")
    fun parsingProductName01() {
        // given
        val name = "상)품명["

        every { filter.doFilter(any()) } returns name

        // when
        // then
        assertThatCode { policy.parsingProductName(name) }.doesNotThrowAnyException()
    }

    @Test
    @DisplayName("(),[],+,-,&,/,_외 특수문자는 오류 발생")
    fun parsingProductName02() {
        // given
        val name = "상품명**"

        every { filter.doFilter(any()) } returns name

        // when
        // then
        assertThatThrownBy { policy.parsingProductName(name) }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("비속어가 들어가면 오류 발생")
    fun parsingProductName03() {
        // given
        val name = "시1발"

        every { filter.doFilter(any()) } throws IllegalArgumentException("통신에 실패했습니다.")

        // when
        // then
        assertThatThrownBy { policy.parsingProductName(name) }.isInstanceOf(IllegalArgumentException::class.java)
    }

}