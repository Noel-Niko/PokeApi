package com.livingTechUSA.Ability.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livingTechUSA.pokemon.database.entity.AbilityEntity

@Dao
interface AbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(Ability: AbilityEntity)


    @Query("DELETE FROM AbilityTable WHERE uniqueId = :id OR name =:AbilityName")
    suspend fun removeAbility(id: Long, AbilityName: String)

    @Query("SELECT * FROM AbilityTable ")
    suspend fun getAllFromAbilityTable(): List<AbilityEntity>

    @Query("SELECT * FROM AbilityTable LIMIT 1")
    suspend fun getOneFromAbilityTable(): AbilityEntity

    @Query("DELETE FROM AbilityTable ")
    suspend fun clearAbilityTable()
}

