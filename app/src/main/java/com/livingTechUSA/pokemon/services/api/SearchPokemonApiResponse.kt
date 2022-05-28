package com.livingTechUSA.pokemon.services.api

import com.livingTechUSA.pokemon.models.Pokemon

class SearchPokemonApiResponse {
    val name: String = "search name"
    val id: String = "search id"
    var pokemon: Pokemon = Pokemon(name)
}