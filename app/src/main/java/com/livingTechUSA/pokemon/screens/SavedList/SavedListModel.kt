package com.livingTechUSA.pokemon.screens.SavedList

import android.content.Context
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.PokemonApiResponse
import com.livingTechUSA.pokemon.services.remoteService.RequestManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class SavedListModel : CoroutineScope, KoinComponent {
    private val mContext: Context by inject()
    private val appDispatchers: IAppDispatchers by inject()
    private val pokemonList: MutableList<Pokemon> = mutableListOf()

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = appDispatchers.ui() + job

    private var pokemon: String = ""
    private var mSearchQuery: String = ""
    private var mIsSearchSelected = false
    private var mIsSearchQueryListenerEnable = true
    private var mIsPokemonSelected = false

    val manager: RequestManager = RequestManager(mContext)

    suspend fun pokemonApiResponse(): PokemonApiResponse? =
        manager.getPokemon()


    fun changeSearchQuery(query: String) {
        mSearchQuery = query
    }

    fun getSearchQuery(): String = mSearchQuery

    fun clearSearchQuery() {
        mSearchQuery = ""
    }

    fun addPokemon(pokemonList: List<Pokemon>) =
        this.pokemonList.addAll(pokemonList)

    fun getPokemon(): List<Pokemon> = pokemonList.toList()

    fun clearPokemon() {
        pokemonList.clear()
    }

    fun isSearchSelected(): Boolean = mIsSearchSelected

    fun setSearchSelected(isSearchSelected: Boolean) {
        mIsSearchSelected = isSearchSelected
    }

    fun isSearchQueryListenerEnable(): Boolean = mIsSearchQueryListenerEnable

    fun changeSearchQueryListenerState(enable: Boolean) {
        mIsSearchQueryListenerEnable = enable
    }


    fun isPokemonSelected(): Boolean = mIsPokemonSelected

    fun setIsPokemonSelected(isSelected: Boolean) {
        mIsPokemonSelected = isSelected
    }


}