package com.livingTechUSA.pokemon.screens.SavedList

import com.livingTechUSA.pokemon.models.Pokemon

interface SavedListView {

    fun showNoPokemonFound(show: Boolean)
    fun navigateToPokemonDetail(pokemon: Pokemon)
    suspend fun updateList(pokemonList: List<Pokemon>)
    fun initPresenter(): SavedListPresenter
    suspend fun showPokemon(pokemonList: List<Pokemon>)
}