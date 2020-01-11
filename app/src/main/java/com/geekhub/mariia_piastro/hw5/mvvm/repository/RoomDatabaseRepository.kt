package com.geekhub.mariia_piastro.hw5.mvvm.repository

import androidx.lifecycle.LiveData
import com.geekhub.mariia_piastro.hw5.mvvm.MainApplication
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.room.UserRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomDatabaseRepository {

    val db = UserRoomDatabase.instance
    val mUserDao = db.userDao()

    fun insert(user: User) {
        GlobalScope.launch {
            mUserDao.insert(user)
        }
    }

    fun load(login: String): LiveData<User>? {
        return mUserDao.load(login)
    }
}