package com.axxess.palliative

import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity


object RoomTestUtil {

    fun createPokemonEntity() = PokemonEntity(
        name = "a name",
        url = "url...",
        imageUrl = "https://...",
        )

}