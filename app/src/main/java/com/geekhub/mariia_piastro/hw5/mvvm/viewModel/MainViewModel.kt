package com.geekhub.mariia_piastro.hw5.mvvm.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekhub.mariia_piastro.hw5.mvvm.R
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.repository.RoomDatabaseRepository
import com.geekhub.mariia_piastro.hw5.mvvm.repository.UserRepository
import com.geekhub.mariia_piastro.hw5.mvvm.ui.UserFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository by lazy {
        UserRepository()
    }

    private val _loggedInUser = MutableLiveData<User?>()
    val loggedInUser: LiveData<User?> = _loggedInUser

    private val _isShowSpinner = MutableLiveData<Boolean>(false)
    val isShowSpinner: LiveData<Boolean> = _isShowSpinner

    //TODO: move this into separete UserViewModel
    fun load (login: String): LiveData<User> {
        TODO()
    }

    fun onLoginClicked(login: String) {
        if (login.isNotEmpty()) {
            GlobalScope.launch {
                try {
                    withContext(Dispatchers.Main) {
                        _isShowSpinner.value = true
                    }

                    val user = userRepository.getUser(login)
                    withContext(Dispatchers.Main) {
                        _loggedInUser.value = user
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                } finally {
                    withContext(Dispatchers.Main) {
                        _isShowSpinner.value = false
                    }
                }
            }
        } else {
            Toast.makeText(getApplication(), "Enter login", Toast.LENGTH_LONG).show()
        }
    }
}