package com.testexample.pokeapi.domain.model

enum class EndpointsUrl(val url: String) {
    GET_POKEMON_LIST("api/v2/pokemon/"),
    GET_POKEMON_DETAIL("api/v2/pokemon/")
}