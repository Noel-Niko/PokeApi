package com.livingTechUSA.pokemon.screens.SavedList

import com.livingTechUSA.pokemon.Base.BasePresenter
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.PokemonApiResponse
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.localService.ILocalService
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class SavedListPresenter(
    private val mView: SavedListView,
    private val mModel: SavedListModel
): BasePresenter(), CoroutineScope, KoinComponent {
    private val localService: ILocalService by inject()
    private val appDispatchers: IAppDispatchers by inject()

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
    get() = job + appDispatchers.ui()
    private val BEGIN_SEARCH_AFTER_MILLIS = 500L

    private var update = false
    private var searchQuery: String = ""

    override fun onCreated() {
        super.onCreated()
        launch(appDispatchers.io()) {
            val list = initPokemonList()
            if (list != null) {
                mModel.addPokemon(
                    list
                )
            }
            val pokemonList = mModel.getPokemon()
            mView.showPokemon(pokemonList)
          //  mView.setSearchQueryTextListener()
        }
    }

        override fun onResume() {
            super.onResume()
            changeSearchQueryListenerState(true)
        }

//    private var searchJob: Job? = null
//
//    fun search(query: String) {
//        searchJob?.cancel()
//        searchJob = launch {
//            delay(BEGIN_SEARCH_AFTER_MILLIS)
//            mModel.changeSearchQuery(query)
//            mView.showRecyclerViewLoader()
//            launch(appDispatchers.ui()){
//                mView.showLoading(true)
//            }
//            mModel.clearArticles()
//            launch {
//                getArticles()
//            }
//        }
//    }
//
//    private suspend fun getArticles() {
//        if(update){
//            mModel.changeCountry(country)
//            mModel.changeCategory(category)
//            mModel.changeSearchQuery(searchQuery)
//        }
//        val call = mModel.getNewsHeadlines()
//        try {
//            call?.articles?.let {
//                val list = mutableListOf<Article>()
//                for(each in it)(
//                        list.add(each.toNewModel())
//                        )
//                updateList(list) }
//        } catch (e:Exception){
//           e.printStackTrace()
//            mView.showErrorMessage(true, "Error attempting to obtain news.")
//        }
//    }
//
//    fun updateList(articleList: List<Article>) {
//        mModel.addArticles(articleList)
//        launch(appDispatchers.io()) {  mView.updateList(articleList) }
//
//    }
//
//    fun clearSearchText() {
//        mModel.clearSearchQuery()
//    }
//    fun onSearchQueryChange(
//        query: String,
//    ) {
//        if (isSearchQueryListenerEnable()) {
//            val showEndDrawable = query.isNotEmpty()
//            mView.showSearchViewEndDrawable(showEndDrawable)
//            search(query)
//        }
//    }

    private fun isSearchQueryListenerEnable(): Boolean = mModel.isSearchQueryListenerEnable()

    private fun changeSearchQueryListenerState(enable: Boolean) {
        mModel.changeSearchQueryListenerState(enable)
    }

    fun isSearchSelected(): Boolean = mModel.isSearchSelected()

    fun setSearchSelected(isSearchSelected: Boolean) {
        mModel.setSearchSelected(isSearchSelected)
    }

    suspend fun initPokemonList(): List<Pokemon>? {
       val list: List<Pokemon> = localService.getAllFromPokemonTable()
       return  list
    }

    override fun onPokemonSelected(pokemon: Pokemon) {
        mModel.setIsPokemonSelected(true)
        mView.navigateToPokemonDetail(pokemon)
    }

}