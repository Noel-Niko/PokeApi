package com.livingTechUSA.pokemon.services.koin.module

import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.PokemonDatabase
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.localService.ILocalService
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.localService.LocalServiceProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

val DatabaseModule = module{
    single { PokemonDatabase.provideRoomDatabase(androidApplication())}
    single { get<PokemonDatabase>().abilityDao() }
    single { get<PokemonDatabase>().pokemonDao() }
    single { LocalServiceProvider(get()) } bind ILocalService::class
}