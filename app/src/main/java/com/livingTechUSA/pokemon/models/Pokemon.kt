package com.livingTechUSA.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    var name: String = "",
    var url: String = "",
    var imageUrl: String = ""
) : Parcelable {
}