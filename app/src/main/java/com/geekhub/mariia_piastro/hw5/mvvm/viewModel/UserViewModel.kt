package com.geekhub.mariia_piastro.hw5.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.repository.RoomDatabaseRepository
import com.geekhub.mariia_piastro.hw5.mvvm.repository.UserRepository

class UserViewModel : ViewModel() {

    private val roomDatabaseRepository = RoomDatabaseRepository()
    private val userRepository = UserRepository()

    fun load (login: String): LiveData<User> {
        return roomDatabaseRepository.load(login)!!
    }

    fun getUser (login: String): LiveData<User> {
       return userRepository.getUser(login)
    }
}