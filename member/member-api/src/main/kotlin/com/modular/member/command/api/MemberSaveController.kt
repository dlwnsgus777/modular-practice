package com.modular.member.command.api

import com.modular.member.command.api.dto.MemberSaveRequestV1
import com.modular.member.command.executor.MemberSaveExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원")
@RestController
@RequestMapping("/api/v1/members")
class MemberSaveController(
    private val memberSaveExecutor: MemberSaveExecutor
) {

    @Operation(summary = "회원 가입")
    @PostMapping
    fun saveMember(@RequestBody request: MemberSaveRequestV1): ResponseEntity<Unit> {
        memberSaveExecutor.execute(request)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "회원 가입")
    @GetMapping
    fun getMember(): ResponseEntity<String> {

        return ResponseEntity.ok("test")
    }
}