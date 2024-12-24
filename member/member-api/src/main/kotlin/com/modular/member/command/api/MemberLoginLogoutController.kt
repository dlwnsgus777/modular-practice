package com.modular.member.command.api

import com.modular.member.command.api.dto.MemberLoginRequestV1
import com.modular.member.command.api.dto.MemberLoginResponseV1
import com.modular.member.command.executor.MemberLoginExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원")
@RestController
@RequestMapping("/api/v1/members")
class MemberLoginLogoutController(
    private val memberLoginExecutor : MemberLoginExecutor
) {

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun loginMember(@RequestBody request: MemberLoginRequestV1): ResponseEntity<MemberLoginResponseV1> {
        val (accessToken, refreshToken) = memberLoginExecutor.execute(request)
        return ResponseEntity.ok(MemberLoginResponseV1(accessToken, refreshToken))
    }

}
