package com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemonTable")
data class PokemonEntity(
    @PrimaryKey
    var name: String = "Table Placeholder",
    var url: String = "Table Placeholder",
    var imageUrl: String = "Table Placeholder"
){
}