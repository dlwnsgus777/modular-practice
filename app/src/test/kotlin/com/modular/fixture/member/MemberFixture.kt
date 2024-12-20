package com.modular.fixture.member

import com.modular.member.command.domain.Member
import jakarta.validation.constraints.Email

object MemberFixture {
    fun aMember(
        email: String = "test@test.com",
        password: String = "password"
    ): Member {
        return Member(
            email = email,
            password = password
        )
    }
}