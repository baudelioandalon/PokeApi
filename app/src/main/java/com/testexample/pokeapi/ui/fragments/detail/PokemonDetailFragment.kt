package com.testexample.pokeapi.ui.fragments.detail

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.testexample.pokeapi.R
import com.testexample.pokeapi.databinding.FragmentDetailPokemonBinding
import com.testexample.pokeapi.databinding.ItemAbilitieBinding
import com.testexample.pokeapi.databinding.ItemTypeBinding
import com.testexample.pokeapi.domain.model.pokemondetail.Ability
import com.testexample.pokeapi.domain.model.pokemondetail.Type
import com.testexample.pokeapi.ui.activities.main.PokemonViewModel
import com.testexample.utils.base.BaseFragment
import com.testexample.utils.util.GAdapter

class PokemonDetailFragment : BaseFragment<FragmentDetailPokemonBinding>() {

    val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)
    }

    val adapterAbilities by lazy {
        GAdapter<ItemAbilitieBinding, Ability>(
            R.layout.item_abilitie,
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<Ability>() {
                override fun areItemsTheSame(oldItem: Ability, newItem: Ability) =
                    oldItem.ability.name == newItem.ability.name

                override fun areContentsTheSame(oldItem: Ability, newItem: Ability) =
                    oldItem == newItem
            }).build(),
            holderCallback = { binding, model, list, adapter ->
                binding.apply {
                    txtAbilitie.text = model.ability.name
                    txtUrl.text = model.ability.url
                }
            })
    }

    val adapterTypes by lazy {
        GAdapter<ItemTypeBinding, Type>(
            R.layout.item_type,
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<Type>() {
                override fun areItemsTheSame(oldItem: Type, newItem: Type) =
                    oldItem.type.name == newItem.type.name

                override fun areContentsTheSame(oldItem: Type, newItem: Type) =
                    oldItem == newItem
            }).build(),
            holderCallback = { binding, model, list, adapter ->
                binding.apply {
                    txtType.text = model.type.name
                    txtUrl.text = model.type.url
                }
            })
    }

    override fun getLayout() = R.layout.fragment_detail_pokemon

    override fun initView() {
        initElements()
    }

    override fun initObservers() {

    }
}