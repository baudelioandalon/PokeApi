package com.testexample.pokeapi.ui.fragments.main.usecase

import androidx.lifecycle.Observer
import com.testexample.pokeapi.domain.model.pokemondetail.PokemonDetailResponseModel
import com.testexample.pokeapi.domain.model.pokemonlist.PokemonListResponseModel
import com.testexample.utils.core.GErrorResponse
import com.testexample.utils.core.GRequestModel
import com.testexample.utils.core.GResponse

interface UseCasePokemon {
    fun callGetPokemonList(observer: Observer<GResponse<PokemonListResponseModel, GErrorResponse, GRequestModel<Void>>>)
    fun callGetDetail(
        idPokemon: String,
        observer: Observer<GResponse<PokemonDetailResponseModel, GErrorResponse, GRequestModel<Void>>>
    )
}