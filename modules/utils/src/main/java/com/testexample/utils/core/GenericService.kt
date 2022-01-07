package com.testexample.utils.core

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GenericService {
    @GET
    fun serviceResponseGetNoBody(@Url url: String): Call<Any>
}