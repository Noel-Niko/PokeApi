package com.livingTechUSA.pokemon.services.api

import com.livingTechUSA.pokemon.services.webservices.CallAbilityApi
import com.livingTechUSA.pokemon.services.webservices.CallPokemonApi

interface PokemonWebServices {
    fun providePokemonWebService(): CallPokemonApi
    fun provideAbilityWebService(): CallAbilityApi
}