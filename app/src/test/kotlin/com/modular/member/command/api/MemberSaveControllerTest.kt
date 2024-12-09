package com.modular.member.command.api

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import com.fasterxml.jackson.databind.ObjectMapper
import com.modular.support.IntegrationTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions


@IntegrationTest
class MemberSaveControllerTest(

) {
    @Autowired
    lateinit var mockMvc: MockMvc
    val mapper = ObjectMapper()

    @Test
    @DisplayName("회원 가입")
    fun saveMember01() {
        // given
        val request = MemberSaveRequest(
            email = ""
        )

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { print() }

        // then
        resultActions.andExpect { status().isOk }

    }

    class MemberSaveRequest(email: String) {

    }

}