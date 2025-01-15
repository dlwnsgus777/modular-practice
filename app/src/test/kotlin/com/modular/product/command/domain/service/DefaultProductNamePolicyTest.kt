package com.modular.product.command.domain.service

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DefaultProductNamePolicyTest {

    @Test
    @DisplayName("특수 문자는 (),[],+,-,&,/,_ 만 허용")
    fun validate01() {
        // given
        val name = "상)품명["
        val policy = DefaultProductNamePolicy()

        // when
        // then
        assertThatCode { policy.validate(name) }.doesNotThrowAnyException()
    }

    @Test
    @DisplayName("(),[],+,-,&,/,_외 특수문자는 오류 발생")
    fun validate02() {
        // given
        val name = "상품명**"
        val policy = DefaultProductNamePolicy()

        // when
        // then
        assertThatThrownBy { policy.validate(name) }.isInstanceOf(IllegalArgumentException::class.java)
    }

}