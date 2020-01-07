package com.geekhub.mariia_piastro.hw5.mvvm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekhub.mariia_piastro.hw5.mvvm.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users WHERE login = :userLogin")
    fun load(userLogin: String): LiveData<User>
}