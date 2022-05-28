package com.livingTechUSA.pokemon.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "abilityTable")
data class AbilityEntity (
    @PrimaryKey(autoGenerate = true)
    var uniqueId: Long = 0L,
    val pokemonName: String = "Place Holder",
    val name: String = "Place Holder",
    val url: String = "Place Holder"
)
