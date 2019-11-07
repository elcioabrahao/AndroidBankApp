package com.trantordev.androidbankapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trantordev.androidbankapp.R
import com.trantordev.androidbankapp.viewmodel.StatementViewModel

class StatementFragment : Fragment() {

    companion object {
        fun newInstance() = StatementFragment()
    }

    private lateinit var viewModel: StatementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.statement_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatementViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
