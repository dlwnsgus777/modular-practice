package com.modular.member.command.executor

import com.modular.member.command.api.dto.MemberSaveRequestV1
import com.modular.member.command.domain.service.MemberService
import org.springframework.stereotype.Component

@Component
class MemberSaveExecutor(
    private val memberService: MemberService
) {

    fun execute(request: MemberSaveRequestV1) {
        val input = request.toInput()
        memberService.save(input)
    }

}
