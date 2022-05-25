package com.livingTechUSA.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String = "",
    val url: String = ""
) : Parcelable {
}