package com.livingTechUSA.pokemon.util

import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity

fun Pokemon.toEntity() = PokemonEntity(
    name,
    url,
    imageUrl
)

fun PokemonEntity.toModel() = Pokemon(
    name,
    url,
    imageUrl
)
