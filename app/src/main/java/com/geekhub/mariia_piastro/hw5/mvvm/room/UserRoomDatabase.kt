package com.geekhub.mariia_piastro.hw5.mvvm.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekhub.mariia_piastro.hw5.mvvm.MainApplication
import com.geekhub.mariia_piastro.hw5.mvvm.model.User

@Database(entities = [User::class], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val instance: UserRoomDatabase by lazy {
            Room.databaseBuilder(
                MainApplication.applicationContext(),
                UserRoomDatabase::class.java,
                "users.bd"
            ).build()
        }
    }
}