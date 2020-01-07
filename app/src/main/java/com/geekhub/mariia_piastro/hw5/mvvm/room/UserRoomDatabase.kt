package com.geekhub.mariia_piastro.hw5.mvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekhub.mariia_piastro.hw5.mvvm.model.User

@Database(entities = [User::class], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserRoomDatabase? = null
        private val LOCK = Any()

        fun getUserRoomDatabase(context: Context):UserRoomDatabase? {
            if (instance == null) {
                synchronized(LOCK){
                    instance = Room.databaseBuilder(context, UserRoomDatabase::class.java, "users.bd").build()
                }
            }
            return instance
        }
    }
}