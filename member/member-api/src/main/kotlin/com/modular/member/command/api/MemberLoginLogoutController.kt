package com.modular.member.command.api

import com.modular.member.command.api.dto.MemberLoginRequestV1
import com.modular.member.command.api.dto.MemberLoginResponseV1
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
) {

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun loginMember(@RequestBody request: MemberLoginRequestV1): ResponseEntity<String> {

        return ResponseEntity.ok("test")
    }

}
