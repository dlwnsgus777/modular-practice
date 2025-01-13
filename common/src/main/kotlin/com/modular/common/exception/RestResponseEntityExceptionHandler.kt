package com.modular.common.exception

import org.apache.coyote.BadRequestException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.nio.file.AccessDeniedException
import javax.security.sasl.AuthenticationException

@RestControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(
        AccessDeniedException::class,
        AuthorizationDeniedException::class
    )
    fun handleAccessDeniedException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ErrorResponse(HttpStatus.FORBIDDEN, e.message))
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(
        AuthenticationException::class
    )
    fun handleUnAuthenticationException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(HttpStatus.UNAUTHORIZED, e.message))
    }

    @ExceptionHandler(BadRequestException::class)
    protected fun handleApiException(e: AbstractBaseException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(e.httpStatus)
            .body(
                ErrorResponse(
                    e.httpStatus.value(),
                    e.httpStatus.reasonPhrase,
                    e.message,
                    null
                )
            )
    }

    @ExceptionHandler(AbstractBaseException::class)
    protected fun handleCustomException(e: AbstractBaseException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(e.httpStatus)
            .body(
                ErrorResponse(
                    e.httpStatus.value(),
                    e.httpStatus.reasonPhrase,
                    e.message,
                    null
                )
            )
    }

    @ExceptionHandler(DataAccessException::class)
    protected fun handleUnUncategorizedSQLException(e: DataAccessException): ResponseEntity<ErrorResponse> {
        log.error("SQL Exception: {}", e.message, e)

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                    HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                    null
                )
            )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("Error processing request: {}", e.message, e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.message,
                )
            )
    }

    @ExceptionHandler(Exception::class)
    protected fun exception(e: Exception): ResponseEntity<ErrorResponse> {
        log.error(
            """
                          Error: {}
                          HttpStatus: {} {}
                          Message: {}
                          """.trimIndent(),
            e, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            e.message
        )
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                    e.localizedMessage, null
                )
            )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}