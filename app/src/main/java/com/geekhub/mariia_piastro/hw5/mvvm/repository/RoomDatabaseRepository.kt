package com.geekhub.mariia_piastro.hw5.mvvm.repository

import androidx.lifecycle.LiveData
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.room.UserRoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RoomDatabaseRepository {

    val db = UserRoomDatabase.instance
    val mUserDao = db.userDao()

    fun insert(user: User) {
        GlobalScope.launch {
            mUserDao.insert(user)
        }
    }

    suspend fun load(login: String): User? = suspendCoroutine {
        val user = mUserDao.load(login)
        it.resume(user)
    }

}