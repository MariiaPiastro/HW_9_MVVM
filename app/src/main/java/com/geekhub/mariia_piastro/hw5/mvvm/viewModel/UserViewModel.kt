package com.geekhub.mariia_piastro.hw5.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.repository.RoomDatabaseRepository
import com.geekhub.mariia_piastro.hw5.mvvm.repository.UserRepository

class UserViewModel() : ViewModel() {

    private val roomDatabaseRepository = RoomDatabaseRepository()
    private val userRepository = UserRepository()
    private var mUser: LiveData<User>
    private var retroObservable: LiveData<User>
    private lateinit var login: String

    init {
        mUser = userRepository.getUser(login)
        retroObservable = roomDatabaseRepository.load(login)!!
    }

    fun load (login: String): LiveData<User> {
        return mUser
    }
}