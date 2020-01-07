package com.geekhub.mariia_piastro.hw5.mvvm.network

import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{user}")
    fun getUser(@Path("user") login: String): Call<User>
}