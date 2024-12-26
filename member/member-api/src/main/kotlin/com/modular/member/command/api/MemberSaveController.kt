package com.modular.member.command.api

import com.modular.member.command.api.dto.MemberSaveRequestV1
import com.modular.member.command.domain.Member
import com.modular.member.command.domain.repository.MemberRepository
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
    private val memberSaveExecutor: MemberSaveExecutor,
    private val memberRepository: MemberRepository
) {

    @Operation(summary = "회원 가입")
    @PostMapping
    fun saveMember(@RequestBody request: MemberSaveRequestV1): ResponseEntity<Unit> {
        memberSaveExecutor.execute(request)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<List<Member>> {
        val list = memberRepository.findAll()
        return ResponseEntity.ok(list)
    }

}
