package com.geekhub.mariia_piastro.hw5.mvvm.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.geekhub.mariia_piastro.hw5.mvvm.R
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var model: UserViewModel? = null
    private var userLogin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(UserViewModel::class.java)


        buttonFindUser.setOnClickListener {
            val userLogin = editTextLogin.text.toString().trim()
            if (userLogin.isNotEmpty()) {
                val mUser = model?.getUser(userLogin)?.value as User
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container, UserFragment.newInstance(mUser)
                    )
                    .commit()
            } else {
                Toast.makeText(this, "Enter login", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("login", userLogin)
    }
}
