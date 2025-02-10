package com.modular.member.command.domain.repository

import com.modular.member.command.domain.Member

interface MemberRepository {
    fun findByEmail(email: String): Member?
    fun save(member: Member): Member
    fun findAll(): List<Member>
}