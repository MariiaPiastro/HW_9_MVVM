package com.geekhub.mariia_piastro.hw5.mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.network.ApiFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun getUser(login: String): LiveData<User> {
        val data = MutableLiveData<User>()

        ApiFactory.getUser(login)
            .enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val roomDatabaseRepository = RoomDatabaseRepository()
                        roomDatabaseRepository.insert(response.body()!!)
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
        return data
    }

}