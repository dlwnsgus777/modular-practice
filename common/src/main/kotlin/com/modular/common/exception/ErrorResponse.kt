package com.modular.common.exception

import org.springframework.http.HttpStatus
import org.springframework.validation.Errors

data class ErrorResponse(
    val status: Int,
    val errorMessage: String?,
    val detailMessage: String?,
    val errors: Errors?
) {
    constructor(status: HttpStatus, errorMessage: String?) : this(status.value(), errorMessage, null, null)
}