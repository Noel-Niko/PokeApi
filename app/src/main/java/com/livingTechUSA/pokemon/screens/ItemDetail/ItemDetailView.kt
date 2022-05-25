package com.livingTechUSA.pokemon.screens.ItemDetail

import com.livingTechUSA.pokemon.models.Ability

interface ItemDetailView {
    fun initPresenter(): ItemDetailPresenter
    suspend fun navigateToSavedPokemon()
    suspend fun updateList(abilityList: List<Ability>)
    fun showNoAbilitiesFound(show: Boolean)
}