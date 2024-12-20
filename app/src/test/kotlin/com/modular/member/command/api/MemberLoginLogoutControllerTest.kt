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
import org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class MemberLoginLogoutControllerTest : IntegrationTestController() {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Test
    @DisplayName("회원 로그인")
    fun loginMember01() {
        // given
        val member = MemberFixture.aMember()
        memberRepository.save(member)
        entityManager.flush()

        val request: MemberLoginRequestV1 = MemberLoginRequestV1(
            email = member.email,
            password = member.password
        )

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/members/{memberId}/login", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { print() }

        // then
        resultActions.andExpect { status().isOk }
            .andExpect { jsonPath("$.accessToken").isNotEmpty }
            .andExpect { jsonPath("$.refreshToken").isNotEmpty }
    }

}