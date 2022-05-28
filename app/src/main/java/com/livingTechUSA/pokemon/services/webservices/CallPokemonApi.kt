package com.livingTechUSA.pokemon.services.webservices

import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.services.api.AbilityApiResponse
import retrofit2.http.GET
import com.livingTechUSA.pokemon.services.api.PokemonApiResponse
import com.livingTechUSA.pokemon.services.api.SearchPokemonApiResponse
import retrofit2.http.Path

interface CallPokemonApi {

    @GET("api/v2/pokemon?limit=100&offset=0")
    suspend fun callPokemon(
    ): PokemonApiResponse?


    @GET("api/v2/pokemon/{name}/")
    suspend fun findAPokemon(@Path("name") name: String): SearchPokemonApiResponse?
//    {
//        "count": 248,
//        "next": "https://pokeapi.co/api/v2/ability/?limit=20&offset=20",
//        "previous": null,
//        "results": [
//        {
//            "name": "stench",
//            "url": "https://pokeapi.co/api/v2/ability/1/"
//        }
//        ]
//    }

    suspend fun callAbilities(): AbilityApiResponse?
    suspend fun getAbilities(pokemonName: String): AbilityApiResponse?
    suspend fun findPokemon(name: String): SearchPokemonApiResponse?
}