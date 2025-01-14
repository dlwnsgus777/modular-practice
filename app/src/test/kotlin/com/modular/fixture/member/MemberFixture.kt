package com.modular.fixture.member

import com.modular.member.command.domain.Member
import com.modular.common.support.Sha512PasswordEncoder

object MemberFixture {
    private val encoder = Sha512PasswordEncoder()

    fun aMember(
        email: String = "test@test.com",
        password: String = "password"
    ): Member {
        return Member(
            email = email,
            password = encoder.encode(password)
        )
    }
}