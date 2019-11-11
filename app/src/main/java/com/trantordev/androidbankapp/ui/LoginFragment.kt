package com.trantordev.androidbankapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.trantordev.androidbankapp.databinding.LoginFragmentBinding
import com.trantordev.androidbankapp.util.InjectorUtils
import com.trantordev.androidbankapp.util.toast
import com.trantordev.androidbankapp.viewmodel.LoginViewModel

class LoginFragment : Fragment(), LoginListener {


    private lateinit var binding: LoginFragmentBinding

    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        viewModel.loginListener = this
        return binding.root
    }

    override fun onStarted() {
        binding.progressBarLayout.visibility = View.VISIBLE
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        binding.progressBarLayout.visibility = View.GONE
        loginResponse.observe(this, Observer {
            val intent = Intent(this.activity, StatementActivity::class.java)
            startActivity(intent)

        })    }

    override fun onFailure(message: Int) {
        binding.progressBarLayout.visibility = View.GONE
        activity!!.toast(context!!.getString(message))
    }

    companion object{
        val TAG: String = "LoginFragment"
    }
}
