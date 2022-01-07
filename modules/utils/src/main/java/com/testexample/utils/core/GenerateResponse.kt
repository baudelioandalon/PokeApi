package com.testexample.utils.core

import android.util.MalformedJsonException
import androidx.lifecycle.Observer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownServiceException
import kotlin.reflect.KClass

class GenerateResponse<R, G>(
    val observer: Observer<GResponse<R, GErrorResponse, G>>,
    val requestData: G? = null,
    val vkClass: KClass<*>
) {

    fun validationMethod(result: Call<R>) {
        result.enqueue(object : Callback<R> {
            override fun onFailure(call: Call<R>, t: Throwable) {
                if (t is ConnectException) {
                    observer.onChanged(
                        GResponse(
                            GStatusRequestEnum.FAILURE,
                            null,
                            validateTypeError(
                                GErrorResponseEnum.ERROR_CONEXION.messageError
                            ),
                            requestData
                        )
                    )
                } else if (t is MalformedJsonException) {
                    observer.onChanged(
                        GResponse(
                            GStatusRequestEnum.FAILURE,
                            null, validateTypeError(
                                GErrorResponseEnum.ERROR_MALFORMED_ERROR_JSON.messageError
                            ),
                            requestData
                        )
                    )
                } else if (t is UnknownServiceException) {
                    observer.onChanged(
                        GResponse(
                            GStatusRequestEnum.FAILURE,
                            null, validateTypeError(
                                GErrorResponseEnum.ERROR_NETWORK_SECURITY_POLICY.messageError
                            ),
                            requestData
                        )
                    )
                } else if (t is SocketTimeoutException) {
                    observer.onChanged(
                        GResponse(
                            GStatusRequestEnum.FAILURE,
                            null,
                            validateTypeError(
                                GErrorResponseEnum.ERROR_TIMEOUT.messageError,
                            ),
                            requestData
                        )
                    )
                } else {
                    observer.onChanged(
                        GResponse(
                            GStatusRequestEnum.FAILURE,
                            null,
                            validateTypeError(
                                GErrorResponseEnum.ERROR_SYSTEM.messageError
                            ),
                            requestData
                        )
                    )
                }
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
                when (response.code()) {
                    200, 201 -> {
                        val gson = Gson()
                        val jsonObject =
                            gson.toJsonTree(response.body()).asJsonObject
                        val myResponse =
                            Gson().fromJson(jsonObject, vkClass.javaObjectType) as R
                        observer.onChanged(
                            GResponse(
                                GStatusRequestEnum.SUCCESS,
                                myResponse, requestData = requestData
                            )
                        )

                    }
                    else -> {
                        observer.onChanged(
                            GResponse(
                                GStatusRequestEnum.FAILURE,
                                null,
                                validateTypeError(
                                    GErrorResponseEnum.ERROR_SYSTEM.errorCode,
                                    response
                                ),
                                requestData
                            )
                        )

                    }
                }
            }
        })

    }

    fun validateTypeError(
        error: String, response: Response<R>? = null,
        dataErrorCode: String = "",
        dataErrorMessage: String = ""
    ) =
        when (error) {
            GErrorResponseEnum.ERROR_CONEXION.defaultError -> assembleErrorModel(
                GErrorResponseEnum.ERROR_CONEXION.messageError
            )
            GErrorResponseEnum.ERROR_SYSTEM.defaultError -> assembleErrorModel(
                GErrorResponseEnum.ERROR_SYSTEM.messageError,
                response, dataErrorCode, dataErrorMessage
            )
            GErrorResponseEnum.ERROR_MALFORMED_ERROR_JSON.defaultError -> assembleErrorModel(
                GErrorResponseEnum.ERROR_MALFORMED_ERROR_JSON.messageError,
                response, dataErrorCode, dataErrorMessage
            )
            GErrorResponseEnum.ERROR_TIMEOUT.defaultError -> assembleErrorModel(
                GErrorResponseEnum.ERROR_TIMEOUT.messageError,
                response, dataErrorCode, dataErrorMessage
            )
            GErrorResponseEnum.ERROR_NETWORK_SECURITY_POLICY.defaultError -> assembleErrorModel(
                GErrorResponseEnum.ERROR_NETWORK_SECURITY_POLICY.messageError,
                response, dataErrorCode, dataErrorMessage
            )
            else -> assembleErrorModel(
                error,
                response, dataErrorCode, dataErrorMessage
            )
        }

    private fun assembleErrorModel(
        error: String,
        response: Response<R>? = null,
        dataErrorCode: String = "",
        dataErrorMessage: String = ""
    ): GErrorResponse {
        return GErrorResponse(
            9999,
            "Error al obtener los headers por el response",
            errorResult = error
        )
    }

}