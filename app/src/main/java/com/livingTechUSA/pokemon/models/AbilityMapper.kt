package com.livingTechUSA.pokemon.models



fun AbilityRaw.toNewModel(): Ability {
    return Ability(
        name,
        url
    )
}