//package com.livingTechUSA.pokemon.services.deserializer
//
//import com.google.gson.GsonBuilder
//import com.google.gson.JsonDeserializationContext
//import com.google.gson.JsonDeserializer
//import com.google.gson.JsonElement
//import com.livingTechUSA.pokemon.models.Ability
//import java.lang.reflect.Type
//
//class AbilityDeserializer : JsonDeserializer<Ability> {
//
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): Ability {
//        val gson = GsonBuilder().create()
//        val ability = gson.fromJson(json, Ability::class.java)
//
//        return ability
//    }
//}