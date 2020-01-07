package com.geekhub.mariia_piastro.hw5.mvvm.repository

import androidx.lifecycle.LiveData
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.network.ApiFactory
import com.geekhub.mariia_piastro.hw5.mvvm.room.UserDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private var mUserDao: UserDao) {

    private fun getUser(login: String): LiveData<User> {
        ApiFactory.getUser(login)
            .enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        mUserDao.insert(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
        return mUserDao.load(login)
    }

}