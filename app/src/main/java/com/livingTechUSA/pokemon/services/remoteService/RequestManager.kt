package com.livingTechUSA.pokemon.services.remoteService

import android.content.Context
import android.widget.Toast
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.AbilityApiResponse
import com.livingTechUSA.pokemon.services.api.PokemonApiResponse
import com.livingTechUSA.pokemon.services.api.PokemonWebServices
import com.livingTechUSA.pokemon.services.webservices.CallPokemonApi
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RequestManager (var context: Context): CallPokemonApi, CoroutineScope, KoinComponent {
    private val pokemonWebServices: PokemonWebServices by inject()
    private val appDispatchers: IAppDispatchers by inject()

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
    get() = job + appDispatchers.ui()

    suspend fun getPokemon(): PokemonApiResponse? {
        try {
            val _result = coroutineScope {
                async {
                    pokemonWebServices.providePokemonWebService()
                        .callPokemon()
                }
            }
            val result = _result.await()
            return result
        } catch (e: Exception){
            coroutineScope {
                launch(appDispatchers.ui()){
                    Toast.makeText( context, "Unable to download Pokemon." +
                            "\n" +
                            "Do you have and internet connection?", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            e.printStackTrace()
        }
        return null
    }


    override suspend fun callPokemon(
    ): PokemonApiResponse? {
        return callPokemon(
        )
    }

    override suspend fun findAPokemon(name: String): PokemonApiResponse? {
        TODO("Not yet implemented")
    }


    override suspend fun getAbilities(pokemonName: String): AbilityApiResponse? {
        try {
            val _result = coroutineScope {
                async {
                    pokemonWebServices.provideAbilityWebService()
                        .getAbilities(pokemonName)
                }
            }
            val result = _result.await()
            return result
        } catch (e: Exception){
            coroutineScope {
                launch(appDispatchers.ui()){
                    Toast.makeText( context, "Unable to download Pokemon abilities." +
                            "\n" +
                            "Do you have an internet connection?", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            e.printStackTrace()
        }
        return null
    }


    override suspend fun callAbilities(
    ): AbilityApiResponse? {
        return callAbilities(
        )
    }


}