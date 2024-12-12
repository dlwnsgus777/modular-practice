package com.modular.member.command.domain


@Entity
@Table(name = "member")
class Member(
    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}