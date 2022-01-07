package com.testexample.pokeapi.ui.fragments.detail

import com.testexample.pokeapi.domain.model.pokemondetail.Ability
import com.testexample.pokeapi.domain.model.pokemondetail.Type

fun PokemonDetailFragment.initElements() {
    binding.apply {
        pokemonModel = viewModel.getPokemonDetail()
        setAbilitiesList(viewModel.getPokemonDetail().abilities)
        setTypesList(viewModel.getPokemonDetail().types)
    }
}

fun PokemonDetailFragment.setAbilitiesList(list: List<Ability>) {
    binding.apply {
        recyclerAbilities.adapter = adapterAbilities
        adapterAbilities.submitList(list)
    }
}

fun PokemonDetailFragment.setTypesList(list: List<Type>) {
    binding.apply {
        recyclerTypes.adapter = adapterTypes
        adapterTypes.submitList(list)
    }
}