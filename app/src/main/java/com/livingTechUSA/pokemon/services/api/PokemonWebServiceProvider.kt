package com.livingTechUSA.pokemon.services.api


import com.facebook.stetho.okhttp3.StethoInterceptor
import com.livingTechUSA.pokemon.services.webservices.CallAbilityApi
import com.livingTechUSA.pokemon.services.webservices.CallPokemonApi
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonWebServiceProvider: PokemonWebServices, KoinComponent {
    //okHttpClient for Stetho network tracking
    var okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(" https://pokeapi.co")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun providePokemonWebService(): CallPokemonApi {
        val builder = retrofit
        return builder
            .create(CallPokemonApi::class.java)
    }

    override fun provideAbilityWebService(): CallAbilityApi {
        val builder = retrofit
        return builder
            .create(CallAbilityApi::class.java)
    }
}