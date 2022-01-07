package com.testexample.pokeapi.ui.fragments.main

fun PokemonFragment.initElements() {
    binding.apply {
        viewModel.getPokemonList()
    }
}