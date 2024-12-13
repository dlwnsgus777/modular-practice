package com.modular.member.command.repository

import com.modular.member.command.domain.Member
import com.modular.member.command.domain.repository.MemberRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JpaMemberRepository: JpaRepository<Member, Long>, MemberRepository {

}