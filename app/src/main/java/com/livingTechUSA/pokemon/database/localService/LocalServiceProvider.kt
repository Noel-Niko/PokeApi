package com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.localService

import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.util.toEntity
import com.livingTechUSA.pokemon.util.toModel
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.dao.PokemonDao
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity



class LocalServiceProvider(
    private val PokemonDao: PokemonDao
) : ILocalService {
    override suspend fun insertPokemon(Pokemon: Pokemon) {
        val DB = Pokemon.toEntity()
        PokemonDao.insertPokemon(DB)
    }

    override suspend fun removePokemon(Pokemon: Pokemon) {
        val DB = Pokemon.toEntity()
        if (DB.name != null) {
            PokemonDao.removePokemon(DB.name!!)
        }
    }

    override suspend fun clearPokemonTable() {
        PokemonDao.clearPokemonTable()
    }

    override suspend fun getAllFromPokemonTable(): List<Pokemon> {
        val DB: List<PokemonEntity> =  PokemonDao.getAllFromPokemonTable()
        val model = mutableListOf<Pokemon>()
        for(it in DB){
            model.add(it.toModel())
        }
        return model
    }

    override suspend fun getOneFromPokemonTable(): PokemonEntity? {
       return PokemonDao.getOneFromPokemonTable()
    }
}