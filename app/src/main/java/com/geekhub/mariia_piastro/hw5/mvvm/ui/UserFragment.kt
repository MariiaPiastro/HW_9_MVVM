package com.geekhub.mariia_piastro.hw5.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.geekhub.mariia_piastro.hw5.mvvm.R
import com.geekhub.mariia_piastro.hw5.mvvm.model.User
import com.geekhub.mariia_piastro.hw5.mvvm.viewModel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment : Fragment() {

    private lateinit var model: UserViewModel
    private lateinit var login: String
    lateinit var mUser: User

    companion object {
        fun newInstance(login: String): UserFragment {
            val args = Bundle()
            args.putString("login", login)
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            login = arguments!!.getString("login")!!
        }
        model = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.init((login))
        model.selectedUser.observe(this, Observer { user: User? ->
            if (user != null) {
                with(view) {
                    tvLogin.text = String.format("Login: ", mUser.login)
                    tvUrl.text = String.format("URL: ", mUser.url)
                    tvReposUrl.text = String.format("ReposURL: ", mUser.reposUrl)
                    tvType.text = String.format("Type: ", mUser.type)
                    tvName.text = String.format("Name: ", mUser.name)
                    tvCompany.text = String.format("Company: ", mUser.company)
                    tvLocation.text = String.format("Location: ", mUser.location)
                    tvRegistration.text = String.format("URL: ", mUser.registration)
                    Picasso.get().load(mUser.avatarUrl).into(view.imageViewAvatar)
                }
            }
        })
    }
}