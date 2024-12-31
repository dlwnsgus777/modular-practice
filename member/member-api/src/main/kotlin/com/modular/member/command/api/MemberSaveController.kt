package com.modular.member.command.api

import com.modular.auth.domain.CurrentMember
import com.modular.member.command.api.dto.MemberSaveRequestV1
import com.modular.member.command.executor.MemberSaveExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원")
@RestController
@RequestMapping("/api/v1/members")
class MemberSaveController(
    private val memberSaveExecutor: MemberSaveExecutor,
) {

    @Operation(summary = "회원 가입")
    @PostMapping
    fun saveMember(@RequestBody request: MemberSaveRequestV1): ResponseEntity<Unit> {
        memberSaveExecutor.execute(request)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<CurrentMember> {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication == null || !authentication.isAuthenticated) {
            throw IllegalStateException("로그인 계정 없음")
        }

        try {
            val currentMember = authentication.principal as CurrentMember
            return ResponseEntity.ok(currentMember)
        } catch (e: Exception) {
            log.error("CurrentMember error: {}", authentication.principal, e)
            throw IllegalStateException("로그인 계정 변환 실패", e)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
