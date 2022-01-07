package com.testexample.utils.core


class GenerateServices<R>(baseUrl: String) {

    var userService: GenericService = GRestEngine.getRestEngine(baseUrl).create(
        GenericService::class.java
    )

    fun getExecuteService(endPoint: String) =
        userService.serviceResponseGetNoBody(endPoint) as R

}