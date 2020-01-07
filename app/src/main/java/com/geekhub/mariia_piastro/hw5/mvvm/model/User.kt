package com.geekhub.mariia_piastro.hw5.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Long,
    val login: String,
    @Json(name = "avatar_url")
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    val url: String,
    @Json(name = "repos_url")
    @ColumnInfo(name = "repos_url") val reposUrl: String,
    val type: String,
    val name: String,
    val company: String,
    val location: String,
    @Json(name = "created_at")
    @ColumnInfo(name = "created_at") val registration: String
)