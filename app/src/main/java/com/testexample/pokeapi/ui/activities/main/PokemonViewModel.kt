package com.testexample.pokeapi.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testexample.pokeapi.ui.fragments.main.domain.PokemonRepository
import com.testexample.pokeapi.domain.model.pokemondetail.PokemonDetailResponseModel
import com.testexample.pokeapi.domain.model.pokemonlist.PokemonListResponseModel
import com.testexample.utils.core.GErrorResponse
import com.testexample.utils.core.GRequestModel
import com.testexample.utils.core.GResponse

class PokemonViewModel : ViewModel() {

    private val repository by lazy {
        PokemonRepository()
    }

    private val _pokemonSelected = MutableLiveData("")

    val pokemonSelected: LiveData<String>
        get() = _pokemonSelected

    fun setPokemonSelected(pokemonSelected: String) {
        _pokemonSelected.postValue(pokemonSelected)
    }

    fun getPokemonSelected() = pokemonSelected.value ?: ""

    private val _listPokemon =
        GResponse<PokemonListResponseModel, GErrorResponse, GRequestModel<Void>>(
            requestData = GRequestModel()
        )

    val listPokemon: LiveData<GResponse<PokemonListResponseModel, GErrorResponse, GRequestModel<Void>>>
        get() = _listPokemon

    private val _detailPokemon =
        GResponse<PokemonDetailResponseModel, GErrorResponse, GRequestModel<Void>>(
            requestData = GRequestModel()
        )

    val detailPokemon: LiveData<GResponse<PokemonDetailResponseModel, GErrorResponse, GRequestModel<Void>>>
        get() = _detailPokemon

    fun getPokemonList() {
        repository.callGetPokemonList {
            _listPokemon.postValue(it)
        }
    }

    fun executePokemonDetail() {
        repository.callGetDetail(getPokemonSelected()) {
            _detailPokemon.postValue(it)
        }
    }

    fun getPokemonDetail() = _detailPokemon.value?.successData ?: PokemonDetailResponseModel()

}