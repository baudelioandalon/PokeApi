package com.testexample.pokeapi.domain.model.pokemondetail

data class PokemonDetailResponseModel(
    val abilities: List<Ability> = listOf(),
    val name: String = "",
    val sprites: Sprites = Sprites(),
    val types: List<Type> = listOf(),
)