package com.testexample.pokeapi.domain.model.pokemondetail

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)