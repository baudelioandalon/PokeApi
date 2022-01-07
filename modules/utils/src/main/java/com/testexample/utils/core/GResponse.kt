package com.testexample.utils.core

import androidx.lifecycle.MutableLiveData

data class GResponse<S, E, T>(
    val statusRequest: GStatusRequestEnum = GStatusRequestEnum.NONE,
    val successData: S? = null,
    val errorData: E? = null,
    val requestData: T? = null
) : MutableLiveData<GResponse<S, E, T>>()