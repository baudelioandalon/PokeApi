package com.testexample.pokeapi.ui.fragments.main

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.testexample.pokeapi.R
import com.testexample.pokeapi.databinding.FragmentPokemonBinding
import com.testexample.pokeapi.databinding.ItemPokemonBinding
import com.testexample.pokeapi.domain.model.pokemonlist.Result
import com.testexample.pokeapi.ui.activities.main.PokemonViewModel
import com.testexample.utils.base.BaseFragment
import com.testexample.utils.core.GStatusRequestEnum
import com.testexample.utils.extensions.log
import com.testexample.utils.extensions.setOnSingleClickListener
import com.testexample.utils.util.GAdapter

class PokemonFragment : BaseFragment<FragmentPokemonBinding>() {

    val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
    }

    val adapter by lazy {
        GAdapter<ItemPokemonBinding, Result>(
            R.layout.item_pokemon,
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result) =
                    oldItem.name == newItem.name

                override fun areContentsTheSame(oldItem: Result, newItem: Result) =
                    oldItem == newItem
            }).build(),
            holderCallback = { binding, model, list, adapter ->
                binding.apply {
                    txtNamePokemon.text = model.name
                    itemPokemonContainer.setOnSingleClickListener {
                        viewModel.setPokemonSelected(model.name)
                    }
                }
            })
    }

    override fun getLayout() = R.layout.fragment_pokemon

    override fun initView() {
        initElements()
    }

    override fun initObservers() {
        viewModel.listPokemon.observe(this) {
            when (it.statusRequest) {
                GStatusRequestEnum.LOADING -> {
                    "LOADING".log("execute")
                }
                GStatusRequestEnum.SUCCESS -> {
                    "SUCCESS".log("execute")
                    it.successData?.let { successModel ->
                        binding.recyclerViewPokemon.adapter = adapter
                        adapter.submitList(successModel.results)
                    }
                }
                GStatusRequestEnum.FAILURE -> {
                    "FAILURE".log("execute")
                    it.errorData?.let {
                        it
                    }
                }
            }
        }

        viewModel.detailPokemon.observe(this) {
            when (it.statusRequest) {
                GStatusRequestEnum.LOADING -> {
                    "LOADING".log("execute")
                }
                GStatusRequestEnum.SUCCESS -> {
                    "SUCCESS".log("execute")
                    it.successData?.let { successModel ->
                        findNavController().navigate(R.id.action_pokemonFragment_to_pokemonDetailFragment)
                    }
                }
                GStatusRequestEnum.FAILURE -> {
                    "FAILURE".log("execute")
                    it.errorData?.let {
                        it
                    }
                }
            }
        }

        viewModel.pokemonSelected.observe(this) {
            it?.let { pokemonSelected ->
                if (pokemonSelected.isNotEmpty()) {
                    viewModel.executePokemonDetail()
                }
            }
        }
    }
}