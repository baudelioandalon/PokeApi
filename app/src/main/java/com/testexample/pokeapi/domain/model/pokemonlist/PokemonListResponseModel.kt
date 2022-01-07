package com.testexample.pokeapi.domain.model.pokemonlist

data class PokemonListResponseModel(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)