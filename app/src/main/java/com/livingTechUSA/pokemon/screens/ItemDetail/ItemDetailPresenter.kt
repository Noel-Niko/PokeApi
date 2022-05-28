package com.livingTechUSA.pokemon.screens.ItemDetail

import com.livingTechUSA.pokemon.Base.BasePresenter
import com.livingTechUSA.pokemon.models.Ability
import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.AbilityApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ItemDetailPresenter(
    private val mView: ItemDetailView,
    private val mModel: ItemDetailModel
) : BasePresenter(), CoroutineScope, KoinComponent {

    private val appDispatchers: IAppDispatchers by inject()

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + appDispatchers.ui()

    override fun onCreated() {
        super.onCreated()
        launch(appDispatchers.io()) {
            initAbilityList(mModel.getPokemon().name)?.abilities.let {
                val list = mutableListOf<Ability>()
                if (it != null) {
                    for(abilities in it){
                       list.add(abilities.ability)
                    }
                }

                mModel.addAbilities(
                    list
                )
            }
            val abilitiesList = mModel.getAbilities()
            mView.updateList(abilitiesList)
            //  mView.setSearchQueryTextListener()
        }
    }

    suspend fun initAbilityList(pokemonName: String): AbilityApiResponse? {
        return mModel.abilityApiResponse(pokemonName)
    }


    fun setPokemon(newpokemon: Pokemon) {
        mModel.setPokemon(newpokemon)
    }

    fun getPokemon(): Pokemon {
        return mModel.getPokemon()
    }

    fun setBundle(selectedPokemon: Pokemon?) {
        if (selectedPokemon != null) {
            mModel.setPokemon(selectedPokemon)
        }
    }
}
