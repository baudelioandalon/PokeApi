package com.testexample.pokeapi.ui.fragments.main.domain

import androidx.lifecycle.Observer
import com.testexample.pokeapi.BuildConfig
import com.testexample.pokeapi.domain.model.EndpointsUrl
import com.testexample.pokeapi.domain.model.pokemondetail.PokemonDetailResponseModel
import com.testexample.pokeapi.domain.model.pokemonlist.PokemonListResponseModel
import com.testexample.pokeapi.ui.fragments.main.usecase.UseCasePokemon
import com.testexample.utils.core.*
import retrofit2.Call

class PokemonRepository : UseCasePokemon {

    override fun callGetPokemonList(observer: Observer<GResponse<PokemonListResponseModel, GErrorResponse, GRequestModel<Void>>>) {
        val requestModel = GRequestModel<Void>(requestUrl = EndpointsUrl.GET_POKEMON_LIST.url)
        GenerateResponse(observer, requestModel, PokemonListResponseModel::class).validationMethod(
            GenerateServices<Call<PokemonListResponseModel>>(BuildConfig.BASE_URL).getExecuteService(
                requestModel.requestUrl ?: "",
            )
        )
    }

    override fun callGetDetail(
        idPokemon: String,
        observer: Observer<GResponse<PokemonDetailResponseModel, GErrorResponse, GRequestModel<Void>>>
    ) {
        val requestModel =
            GRequestModel<Void>(requestUrl = EndpointsUrl.GET_POKEMON_DETAIL.url + idPokemon)
        GenerateResponse(
            observer,
            requestModel,
            PokemonDetailResponseModel::class
        ).validationMethod(
            GenerateServices<Call<PokemonDetailResponseModel>>(BuildConfig.BASE_URL).getExecuteService(
                requestModel.requestUrl ?: "",
            )
        )
    }

}