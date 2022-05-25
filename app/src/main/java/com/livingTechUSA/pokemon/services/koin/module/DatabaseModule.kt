package com.livingTechUSA.pokemon.services.koin.module

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

val DatabaseModule = module{
//    single { NewsDatabase.provideRoomDatabase(androidApplication())}
//    single { get<NewsDatabase>().articleDao() }
//    single { LocalServiceProvider(get()) } bind ILocalService::class
}