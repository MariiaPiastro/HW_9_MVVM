package com.geekhub.mariia_piastro.hw5.mvvm.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.geekhub.mariia_piastro.hw5.mvvm.BuildConfig
import com.geekhub.mariia_piastro.hw5.mvvm.MainApplication
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(ChuckerInterceptor(MainApplication.applicationContext()))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val gitHubApi: GitHubApi = retrofit.create(GitHubApi::class.java)

    fun getUser(login: String): Call<User> {
        return gitHubApi.getUser(login)
    }

}