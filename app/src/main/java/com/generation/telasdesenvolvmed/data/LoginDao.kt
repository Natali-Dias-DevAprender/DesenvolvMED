package com.generation.telasdesenvolvmed.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLogin(login: Login)

    @Query("SELECT * FROM login_table ORDER BY id ASC")
    fun selectLogin(): LiveData<List<Login>>

    @Query("DELETE FROM login_table")
    fun nukeTable()
}