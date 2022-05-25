package com.livingTechUSA.pokemon.screens.ItemList

import com.livingTechUSA.pokemon.models.Pokemon

interface ItemListView {

    fun showNoPokemonFound(show: Boolean)
    fun navigateToPokemonDetail(pokemon: Pokemon)
    suspend fun updateList(pokemonList: List<Pokemon>)
    fun initPresenter(): ItemListPresenter
    suspend fun showPokemon(pokemonList: List<Pokemon>)
}