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

    private var model: UserViewModel? = null
    private var mUser: User? = null
    private var login = ""


    companion object {
        fun newInstance(user: User): UserFragment {
            val args = Bundle()
            args.putSerializable("user", user)
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
        model?.load(login)?.observe(this, Observer { it ->
            mUser = it
        })
        return inflater.inflate(R.layout.fragment_user, container, false)
           }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDetail = arguments?.getSerializable("user") as User?
        with(view) {
            tvLogin.text = String.format("Login: ", userDetail?.login)
            tvUrl.text = String.format("URL: ", userDetail?.url)
            tvReposUrl.text = String.format("ReposURL: ", userDetail?.reposUrl)
            tvType.text = String.format("Type: ", userDetail?.type)
            tvName.text = String.format("Name: ", userDetail?.name)
            tvCompany.text = String.format("Company: ", userDetail?.company)
            tvLocation.text = String.format("Location: ", userDetail?.location)
            tvRegistration.text = String.format("URL: ", userDetail?.registration)
            Picasso.get().load(userDetail?.avatarUrl).into(view.imageViewAvatar)
        }
    }
}