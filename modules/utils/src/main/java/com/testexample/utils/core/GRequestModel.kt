package com.testexample.utils.core

data class GRequestModel<B>(
    val requestBody: B? = null,
    var requestUrl: String? = null,
    var requestHeaders: HashMap<String, String>? = null
)