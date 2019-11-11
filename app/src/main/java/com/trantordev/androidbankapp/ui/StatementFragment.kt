package com.trantordev.androidbankapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener
import com.trantordev.androidbankapp.R
import com.trantordev.androidbankapp.adapters.StatementAdapter
import com.trantordev.androidbankapp.databinding.StatementFragmentBinding
import com.trantordev.androidbankapp.util.InjectorUtils
import com.trantordev.androidbankapp.viewmodel.StatementViewModel

class StatementFragment : Fragment(), StatementListener {

    private val viewModel: StatementViewModel by viewModels {
        InjectorUtils.provideStatementListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = StatementFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        Log.d("STATEMENTS"," Esta na fragment")
        val adapter = StatementAdapter()
        binding.statementList.adapter = adapter
        binding.viewmodel = viewModel
        viewModel.statementListener = this
        subscribeUi(adapter,binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_statement, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.info_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: StatementAdapter, binding: StatementFragmentBinding) {

        Log.d("STATEMENTS"," Esta na subscribe")
        viewModel.statements.observe(viewLifecycleOwner) { result ->
            Log.d("STATEMENTS","-->"+result.list!!.size)
            adapter.submitList(result.list)
        }

        viewModel.clientInfo.observe(viewLifecycleOwner) { result ->
            Log.d("STATEMENTS","AQUI-->"+result)
            binding.clientNameTextView.text = result.name
            // Variáveis BankAccount e Agency estão com os conteúdos trocados no JSON da API
            // Trocado de posição aqui para corrigir o problema
            binding.accountTextView.text =
                "${result.bankAccount} / ${PresentationUtils.getBankAccountFormated(result.agency)}"
            binding.balanceTextView.text = PresentationUtils.getFormatedCurrency(result.balance!!)
        }

    }

    private fun showAboutDialog() {
        FancyGifDialog.Builder(activity)
            .setTitle(getString(R.string.about_title))
            .setMessage(getString(R.string.about_desc))
            .setPositiveBtnBackground(getString(R.string.about_color))
            .setPositiveBtnText(getString(R.string.about_positiv_text))
            .setNegativeBtnText(getString(R.string.about_negative_text))
            .setGifResource(R.drawable.dev)
            .isCancellable(true)
            .OnPositiveClicked(object : FancyGifDialogListener {
                override fun OnClick() {}
            })
            .OnNegativeClicked(object : FancyGifDialogListener {
                override fun OnClick() {}
            })
            .build()
    }

    override fun onClose(){
        Log.d("STATEMENTS","ONCLOSE() na fragment")
        activity!!.finish()
    }
}
