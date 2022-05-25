package com.axxess.palliative.service.koin.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.livingTechUSA.pokemon.service.coroutines.AppDispatchers
import com.livingTechUSA.pokemon.service.coroutines.IAppDispatchers
import com.livingTechUSA.pokemon.services.api.PokemonWebServiceProvider
import com.livingTechUSA.pokemon.services.api.PokemonWebServices
import org.koin.dsl.bind
import org.koin.dsl.module


val NetworkModule = module {

    single { provideStethoInterceptor() }
    single { provideAppDispatcher() }
    single { PokemonWebServiceProvider() } bind PokemonWebServices::class

}

fun provideAppDispatcher(): IAppDispatchers = AppDispatchers()

fun provideStethoInterceptor(): StethoInterceptor = StethoInterceptor()


