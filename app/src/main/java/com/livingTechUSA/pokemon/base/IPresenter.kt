package com.livingTechUSA.pokemon.Base

import com.livingTechUSA.pokemon.models.Pokemon

interface IPresenter {
    fun onCreated()
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onPokemonSelected(pokemon: Pokemon)
}