package com.testexample.utils.core

enum class GErrorResponseEnum(
    val messageError: String,
    val defaultError: String,
    val errorCode: String
) {
    ERROR_SYSTEM(
        "Falla en el sistema.",
        "Falla en el sistema.",
        "-3"
    ),
    ERROR_MALFORMED_ERROR_JSON(
        "El modelo de respuesta de error es incorrecto",
        "Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path \$",
        "0000"
    ),
    ERROR_NETWORK_SECURITY_POLICY(
        "No se permiten peticiones sin HTTPS",
        "CLEARTEXT communication to 10.112.167.166 not permitted by network security policy",
        "2000"
    ),
    ERROR_TIMEOUT(
        "El servidor superó el tiempo de respuesta",
        "failed to connect to /10.112.167.166 (port 9081) from /192.168.2.194 (port 54152) after 10000ms",
        "3000"
    ),
    ERROR_CONEXION(
        "Tu conexion ha fallado",
        "Error conexion",
        "1012"
    )
}