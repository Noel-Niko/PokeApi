package com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity

@Dao
interface PokemonDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertPokemon(Pokemon: PokemonEntity)


        @Query("DELETE FROM PokemonTable WHERE name =:PokemonName")
        suspend fun removePokemon(PokemonName: String)

        @Query("SELECT * FROM PokemonTable ")
        suspend fun getAllFromPokemonTable(): List<PokemonEntity>

        @Query("SELECT * FROM PokemonTable LIMIT 1")
        suspend fun getOneFromPokemonTable(): PokemonEntity

        @Query("DELETE FROM PokemonTable ")
        suspend fun clearPokemonTable()
    }

