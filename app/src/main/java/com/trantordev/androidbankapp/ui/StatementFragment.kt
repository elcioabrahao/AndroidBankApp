package com.trantordev.androidbankapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.trantordev.androidbankapp.adapters.StatementAdapter
import com.trantordev.androidbankapp.databinding.StatementFragmentBinding
import com.trantordev.androidbankapp.util.InjectorUtils
import com.trantordev.androidbankapp.viewmodel.StatementViewModel

class StatementFragment : Fragment(), StatementListener {

    private lateinit var binding: StatementFragmentBinding

    private val viewModel: StatementViewModel by viewModels {
        InjectorUtils.provideStatementListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatementFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = StatementAdapter()
        binding.statementList.adapter = adapter
        binding.viewmodel = viewModel
        viewModel.statementListener = this
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUi(adapter: StatementAdapter) {

        viewModel.statements.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result.list)
            binding.progressBarStatementLayout.visibility = View.GONE
        }

        viewModel.clientInfo.observe(viewLifecycleOwner) { result ->
            binding.clientNameTextView.text = result.name
            // Variáveis BankAccount e Agency estão com os conteúdos trocados no JSON da API
            // Trocado de posição aqui para corrigir o problema
            binding.accountTextView.text =
                "${result.bankAccount} / ${PresentationUtils.getBankAccountFormated(result.agency)}"
            binding.balanceTextView.text = PresentationUtils.getFormatedCurrency(result.balance!!)
        }

    }

    override fun onClose(){
        activity!!.finish()
    }
}
