package com.geekhub.mariia_piastro.hw5.mvvm.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.geekhub.mariia_piastro.hw5.mvvm.R
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var model: MainViewModel
    private lateinit var userLogin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(MainViewModel::class.java)
        model.loggedInUser.observe(this, Observer { it ->
            if (it != null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container, UserFragment.newInstance(userLogin)
                    )
                    .commit()
            }
        })
        model.isShowSpinner.observe(this, Observer {
            if(it) {
                progressBar.visibility = ProgressBar.VISIBLE
            } else {
                progressBar.visibility = ProgressBar.INVISIBLE
            }
        })


        buttonFindUser.setOnClickListener {
            userLogin = editTextLogin.text.toString().trim()
            model.onLoginClicked(userLogin)
        }
    }
}
