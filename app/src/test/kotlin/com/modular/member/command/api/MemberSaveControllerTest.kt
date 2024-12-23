package com.modular.member.command.api

import com.modular.member.command.api.dto.MemberSaveRequestV1
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.support.IntegrationTestController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class MemberSaveControllerTest: IntegrationTestController() {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    @DisplayName("회원 가입")
    fun saveMember01() {
        // given
        val request = MemberSaveRequestV1(
            email = "test@test.com",
            password = "1234"
        )

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { print() }

        // then
        resultActions.andExpect { status().isOk }
        val member = memberRepository.findByEmail(request.email)
        member?.let {
            assert(it.email == request.email)
        } ?: assert(false)

    }

}