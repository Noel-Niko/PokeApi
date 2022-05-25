package com.livingTechUSA.pokemon.screens.ItemDetail

import android.content.Context
import com.livingTechUSA.pokemon.models.Ability
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.AbilityApiResponse
import com.livingTechUSA.pokemon.services.remoteService.RequestManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ItemDetailModel : CoroutineScope, KoinComponent {
    private val mContext: Context by inject()
    private val appDispatchers: IAppDispatchers by inject()
    private lateinit var pokemon: Pokemon
    private var abilities: MutableList<Ability> = mutableListOf()
    val manager: RequestManager = RequestManager(mContext)

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = appDispatchers.ui() + job



    fun addAbilities(abilityList: List<Ability>) =
        abilities.addAll(abilityList)

    fun getAbilities(): List<Ability> = abilities.toList()

    fun clearAbilities() {
        abilities.clear()
    }

    fun setPokemon(newPokemon: Pokemon) {
        pokemon = newPokemon
    }

    fun getPokemon(): Pokemon {
        return pokemon
    }

    suspend fun abilityApiResponse(pokemonName: String): AbilityApiResponse? = manager.getAbilities(pokemonName)


}