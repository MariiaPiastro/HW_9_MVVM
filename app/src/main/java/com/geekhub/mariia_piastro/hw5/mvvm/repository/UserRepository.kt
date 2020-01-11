package com.geekhub.mariia_piastro.hw5.mvvm.repository

import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.network.ApiFactory
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepository {

    val roomDatabaseRepository by lazy {
        RoomDatabaseRepository()
    }

    suspend fun getUser(login: String): User = suspendCoroutine{
        val response = ApiFactory.getUser(login).execute()
        val user = if (response.isSuccessful) {
            response.body()!!
        } else {
            throw HttpException(response)
        }

        roomDatabaseRepository.insert(user)

        it.resume(user)
    }

}