package com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.livingTechUSA.Ability.database.dao.AbilityDao
import com.livingTechUSA.pokemon.database.entity.AbilityEntity
import com.livingTechUSA.pokemon.util.Constant
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.dao.PokemonDao
import com.livingTechUSA.thatsnewstome.com.livingTechUSA.thatsnewstome.database.entity.PokemonEntity


@Database(
    entities = [ AbilityEntity::class, PokemonEntity::class ] ,
    version = 2,
    exportSchema = false
)



abstract class PokemonDatabase : RoomDatabase() {

    /**
     * Connects the database to the DAO.
     */
    abstract fun abilityDao(): AbilityDao
    abstract fun pokemonDao(): PokemonDao

    companion object {
        fun provideRoomDatabase(context: Context): PokemonDatabase {
            var databaseInstance: PokemonDatabase? = null

            return databaseInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    Constant.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                databaseInstance = instance
                instance
            }
        }
    }
}
