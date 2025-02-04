package com.modular.member.command.api

import com.modular.fixture.member.MemberFixture
import com.modular.member.command.api.dto.MemberLoginRequestV1
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.support.IntegrationTestController
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class c8cMemberLoginLogoutControllerTest : IntegrationTestController() {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Test
    @DisplayName("회원 로그인")
    fun loginMember01() {
        // given
        val password = "password"
        val member = MemberFixture.aMember(password = password)
        memberRepository.save(member)
        entityManager.flush()

        val request: MemberLoginRequestV1 = MemberLoginRequestV1(
            email = member.email,
            password = password
        )

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { print() }

        // then
        resultActions
            .andExpectAll(
                status().isOk,
                jsonPath("$.accessToken").exists(),
                jsonPath("$.refreshToken").exists()
            )
    }

}