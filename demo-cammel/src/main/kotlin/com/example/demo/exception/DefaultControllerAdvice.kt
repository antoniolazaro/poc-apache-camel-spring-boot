package com.example.demo.exception

import org.apache.camel.CamelException
import org.apache.camel.CamelExecutionException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@ResponseStatus(HttpStatus.NOT_FOUND)
class RecordNotFoundException(exception: String?) : RuntimeException(exception) {}

@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbiddenException(exception: String?) : RuntimeException(exception) {}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServerException(exception: String?) : RuntimeException(exception) {}

data class ApiErrorResponse(
    val statusCode: Int?,
    val message: String?,
    val description: String?)




@RestControllerAdvice
class DefaultControllerAdvice {

    @ExceptionHandler(CamelException::class,CamelExecutionException::class, Exception::class,RuntimeException::class)
    fun camelExchangeExceptions(ex: CamelException): ResponseEntity<*>? {
        return ResponseEntity
                .status(499)
                .body<Any>(ApiErrorResponse(500, "Erro", ex.message))
    }


}