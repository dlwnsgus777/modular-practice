package com.modular.member.command.executor

import com.modular.auth.domain.service.LoginLogoutService
import com.modular.auth.domain.service.dto.LoginOutput
import com.modular.member.command.api.dto.MemberLoginRequestV1
import org.springframework.stereotype.Component

@Component
class MemberLoginExecutor(
    private val loginLogoutService: LoginLogoutService
) {

    fun execute(request: MemberLoginRequestV1): LoginOutput {
        return loginLogoutService.login(request.email, request.password)
    }

}
