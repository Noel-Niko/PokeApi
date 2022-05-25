package com.livingTechUSA.pokemon.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

//@Parcelize
data class AbilityRaw (
    @SerializedName("name")
    val name: String = "rawname",
    @SerializedName("url")
    val url: String = "",
    val ability: List<Ability>? = null,
    val abilities: Abilities? = null,
    val base_experience: String = "",
    @SerializedName("height")
    val height: String = "",
)//: Parcelable {}