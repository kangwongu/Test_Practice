package com.testcafekiosk.spring.api

import com.testcafekiosk.spring.api.service.product.response.ProductResponse
import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val code: Int,
    val status: HttpStatus,
    val message: String,
    val data: T,
) {
    constructor(status: HttpStatus, message: String, data: T) :
            this(status.value(), status, message, data)

    companion object {
        fun <T> of(httpStatus: HttpStatus, message: String, data: T): ApiResponse<T> {
            return ApiResponse(httpStatus, message, data)
        }

        fun <T> of(httpStatus: HttpStatus, data: T): ApiResponse<T> {
            return of(httpStatus, httpStatus.name, data)
        }

        fun <T> ok(data: T): ApiResponse<T> {
            return of(HttpStatus.OK, data)
        }
    }
}