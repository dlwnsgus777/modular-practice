package com.modular.member.command.domain.service

import com.modular.member.command.domain.repository.MemberRepository
import com.modular.member.command.domain.service.dto.MemberSaveInput
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun save(input: MemberSaveInput) {
        memberRepository.save(input.toEntity(passwordEncoder))
    }
}