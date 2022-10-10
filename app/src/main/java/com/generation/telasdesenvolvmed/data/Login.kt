package com.generation.telasdesenvolvmed.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_table")
class Login(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var email: String,
    var senha: String
) {
}