package com.livingTechUSA.pokemon.screens.ItemList

import android.text.Editable
import com.livingTechUSA.pokemon.Base.BasePresenter
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.PokemonApiResponse
import com.livingTechUSA.pokemon.services.api.SearchPokemonApiResponse
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ItemListPresenter(
    private val mView: ItemListView,
    private val mModel: ItemListModel
) : BasePresenter(), CoroutineScope, KoinComponent {

    private val appDispatchers: IAppDispatchers by inject()

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + appDispatchers.ui()
    private val BEGIN_SEARCH_AFTER_MILLIS = 500L

    private var update = false
    private var searchQuery: String = ""

    override fun onCreated() {
        super.onCreated()
        initAndShow()
    }

    fun initAndShow() {
        launch(appDispatchers.io()) {
            initPokemonList()?.results?.let {
                val list = mutableListOf<Pokemon>()
                for (each in it) (
                        list.add(each)
                        )
                mModel.addPokemon(
                    list
                )
            }
            val pokemonList = mModel.getPokemon()
            mView.showPokemon(pokemonList)
        }
    }

    suspend fun initPokemonList(): PokemonApiResponse? {
        return mModel.pokemonApiResponse()
    }

    override fun onPokemonSelected(pokemon: Pokemon) {
        mModel.setIsPokemonSelected(true)
        mView.navigateToPokemonDetail(pokemon)
    }

    suspend fun searchByName(text: String?): SearchPokemonApiResponse? {
        return mModel.pokemonApiSearch(text)
    }


}