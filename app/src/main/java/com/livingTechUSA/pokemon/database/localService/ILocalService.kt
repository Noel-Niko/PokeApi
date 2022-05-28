package com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.localService


import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity


interface ILocalService {
    suspend fun insertPokemon(Pokemon: Pokemon)
    suspend fun removePokemon(Pokemon: Pokemon)
    suspend fun clearPokemonTable()
    suspend fun getAllFromPokemonTable(): List<Pokemon>
    suspend fun getOneFromPokemonTable(): PokemonEntity?
}