package com.testexample.utils.core

data class GErrorResponse(
    val code: Int? = null,
    val headersRequest: String? = null,
    val bodyRequest: String? = null,
    val errorBody: String? = null,
    val errorResult: String? = null
)