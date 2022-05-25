package com.livingTechUSA.pokemon.services.webservices

import com.livingTechUSA.pokemon.services.api.AbilityApiResponse
import retrofit2.http.GET
import com.livingTechUSA.pokemon.services.api.PokemonApiResponse
import retrofit2.http.Path

interface CallPokemonApi {

    @GET("api/v2/pokemon?limit=100&offset=0")
    suspend fun callPokemon(
    ): PokemonApiResponse?


    @GET("api/v2/{name}/")
    suspend fun findAPokemon(@Path("name") name: String): PokemonApiResponse?
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

    @GET("/api/v2/pokemon/{name}/")
    suspend fun getAbilities(@Path("name") name: String): AbilityApiResponse?
//    {
//        "id": 1,
//        "name": "stench",
//        "is_main_series": true,
//        "generation": {
//        "name": "generation-iii",
//        "url": "https://pokeapi.co/api/v2/generation/3/"
//    },
//        "names": [
//        {
//            "name": "Stench",
//            "language": {
//            "name": "en",
//            "url": "https://pokeapi.co/api/v2/language/9/"
//        }
//        }
//        ],
//        "effect_entries": [
//        {
//            "effect": "This Pokémon's damaging moves have a 10% chance to make the target [flinch]{mechanic:flinch} with each hit if they do not already cause flinching as a secondary effect.\n\nThis ability does not stack with a held item.\n\nOverworld: The wild encounter rate is halved while this Pokémon is first in the party.",
//            "short_effect": "Has a 10% chance of making target Pokémon [flinch]{mechanic:flinch} with each hit.",
//            "language": {
//            "name": "en",
//            "url": "https://pokeapi.co/api/v2/language/9/"
//        }
//        }
//        ],
//        "effect_changes": [
//        {
//            "version_group": {
//            "name": "black-white",
//            "url": "https://pokeapi.co/api/v2/version-group/11/"
//        },
//            "effect_entries": [
//            {
//                "effect": "Has no effect in battle.",
//                "language": {
//                "name": "en",
//                "url": "https://pokeapi.co/api/v2/language/9/"
//            }
//            }
//            ]
//        }
//        ],
//        "flavor_text_entries": [
//        {
//            "flavor_text": "è‡­ãã¦ã€€ç›¸æ‰‹ãŒ\nã²ã‚‹ã‚€ã€€ã“ã¨ãŒã‚ã‚‹ã€‚",
//            "language": {
//            "name": "ja-kanji",
//            "url": "https://pokeapi.co/api/v2/language/11/"
//        },
//            "version_group": {
//            "name": "x-y",
//            "url": "https://pokeapi.co/api/v2/version-group/15/"
//        }
//        }
//        ],
//        "pokemon": [
//        {
//            "is_hidden": true,
//            "slot": 3,
//            "pokemon": {
//            "name": "gloom",
//            "url": "https://pokeapi.co/api/v2/pokemon/44/"
//        }
//        }
//        ]
//    }
    suspend fun callAbilities(): AbilityApiResponse?
}