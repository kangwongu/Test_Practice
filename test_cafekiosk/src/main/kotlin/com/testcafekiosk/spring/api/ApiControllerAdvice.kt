package com.testcafekiosk.spring.api

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun bindException(e: BindException): ApiResponse<Any> {
        return ApiResponse.of(
            HttpStatus.BAD_REQUEST,
            e.bindingResult.allErrors[0].defaultMessage,
            ""
        )
    }

}