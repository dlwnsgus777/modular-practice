package com.modular.common.support

import org.springframework.security.crypto.password.PasswordEncoder
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

class Sha512PasswordEncoder : PasswordEncoder {
    private val messageDigest: MessageDigest = MessageDigest.getInstance(HASH_ALGORITHM)

    override fun encode(rawPassword: CharSequence): String {
        val salt = getRandomSalt()
        val digest = salt + messageDigest.digest(salt + rawPassword.toString().toByteArray())
        return Base64.getEncoder().encodeToString(digest)
    }

    override fun matches(
        rawPassword: CharSequence,
        encodedPassword: String
    ): Boolean {
        val salt = Base64.getDecoder().decode(encodedPassword).copyOf(SALT_LENGTH)
        val digest = salt + messageDigest.digest(salt + rawPassword.toString().toByteArray())
        return Base64.getEncoder().encodeToString(digest) == encodedPassword
    }

    private fun getRandomSalt(): ByteArray {
        val salt = ByteArray(SALT_LENGTH)
        SecureRandom().nextBytes(salt)
        return salt
    }

    companion object {
        private const val HASH_ALGORITHM = "SHA-512"
        private const val SALT_LENGTH = 8
    }
}
