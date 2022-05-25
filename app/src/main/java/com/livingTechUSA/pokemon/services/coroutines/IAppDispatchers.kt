package com.livingTechUSA.pokemon.service.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface IAppDispatchers {

    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher

}