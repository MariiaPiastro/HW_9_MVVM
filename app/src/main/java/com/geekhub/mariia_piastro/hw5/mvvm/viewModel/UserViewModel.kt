package com.geekhub.mariia_piastro.hw5.mvvm.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.repository.RoomDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val roomDatabaseRepository by lazy {
        RoomDatabaseRepository()
    }

    private val _selectedUser = MutableLiveData<User>()
    val selectedUser: LiveData<User> = _selectedUser

    fun init(login: String) {
        GlobalScope.launch {
            load(login)
        }
    }

    private suspend fun load(login: String) {
        val user = roomDatabaseRepository.load(login)
        withContext(Dispatchers.Main) {
            _selectedUser.value = user
        }
    }
}