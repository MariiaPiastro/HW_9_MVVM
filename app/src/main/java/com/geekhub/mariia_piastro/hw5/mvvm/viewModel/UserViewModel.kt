package com.geekhub.mariia_piastro.hw5.mvvm.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
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

    lateinit var selectedUser: User

    fun load(login: String): User {
        GlobalScope.launch {
            try {
                withContext(Dispatchers.Main){
                    selectedUser = roomDatabaseRepository.load(login)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(getApplication(), e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        return selectedUser
    }
}