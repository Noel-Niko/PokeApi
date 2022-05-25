package com.livingTechUSA.pokemon.Base

import com.livingTechUSA.pokemon.models.Pokemon
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

open class BasePresenter() : IPresenter, KoinComponent, CoroutineScope {

    val appDispatcher: IAppDispatchers by inject()

    override val coroutineContext: CoroutineContext
        get() = appDispatcher.ui() + SupervisorJob()

    override fun onCreated() {
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {

    }

    override fun onStop() {
    }

    override fun onDestroy() {

    }

    override fun onPokemonSelected(pokemon: Pokemon) {

    }

}