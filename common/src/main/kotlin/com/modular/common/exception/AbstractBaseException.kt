package com.modular.common.exception

import org.springframework.http.HttpStatus

abstract class AbstractBaseException(errorMessage: String?, e: Throwable?) : RuntimeException(errorMessage, e) {
    abstract val httpStatus: HttpStatus
}