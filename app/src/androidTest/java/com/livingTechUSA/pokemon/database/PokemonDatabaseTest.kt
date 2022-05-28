package com.livingTechUSA.pokemon.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.axxess.palliative.RoomTestUtil
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.PokemonDatabase
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.dao.PokemonDao
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDatabaseTest {

    private lateinit var db: PokemonDatabase
    private lateinit var pokemonDao: PokemonDao
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java).build()
        pokemonDao= db.pokemonDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testWriteAndReadPokemonEntity() = runBlocking {
        //GIVEN
        val pokemon: PokemonEntity = RoomTestUtil.createPokemonEntity().apply {
            name = "a name"
            url = "url..."
            imageUrl= "https://..."
        }
        pokemonDao.insertPokemon(pokemon)
        //WHEN
        val pE = pokemonDao.getOneFromPokemonTable()
        //THEN
        assertEquals(pE.name, pokemon.name)
    }

}