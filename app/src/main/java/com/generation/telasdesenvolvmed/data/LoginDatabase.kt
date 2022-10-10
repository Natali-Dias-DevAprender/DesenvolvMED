package com.generation.telasdesenvolvmed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Login::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase(){

    abstract fun loginDao() : LoginDao

    companion object{
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatabase(context: Context): LoginDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDatabase::class.java,
                    "login_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}