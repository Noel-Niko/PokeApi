package com.livingTechUSA.pokemon.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    @SerializedName("name")
    val name: String = "abilityname",
    val url: String = ""
)  {
}
