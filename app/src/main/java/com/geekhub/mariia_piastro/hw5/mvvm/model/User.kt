package com.geekhub.mariia_piastro.hw5.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Long,
    val login: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    val url: String,
    @ColumnInfo(name = "repos_url") val reposUrl: String,
    val type: String,
    val name: String,
    val company: String,
    val location: String,
    @ColumnInfo(name = "created_at") val registration: String
)