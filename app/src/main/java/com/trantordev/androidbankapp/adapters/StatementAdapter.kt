package com.trantordev.androidbankapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trantordev.androidbankapp.data.model.AccountStatement
import com.trantordev.androidbankapp.databinding.StatementItemBinding

class StatementAdapter: ListAdapter<AccountStatement, RecyclerView.ViewHolder>(StatementDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val statement = getItem(position)
        (holder as StatementViewHolder).bind(statement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StatementViewHolder(StatementItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    class StatementViewHolder(
        private val binding: StatementItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
//                binding.statement?.let { plant ->
//                    navigateToPlant(plant, it)
//                }
            }
        }

//        private fun navigateToPlant(
//            plant: Plant,
//            it: View
//        ) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    plant.plantId
//                )
//            it.findNavController().navigate(direction)
//        }

        fun bind(item: AccountStatement) {
            binding.apply {
                statement = item
                executePendingBindings()
            }
        }
    }
}

private class StatementDiffCallback : DiffUtil.ItemCallback<AccountStatement>() {

    override fun areItemsTheSame(oldItem: AccountStatement, newItem: AccountStatement): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: AccountStatement, newItem: AccountStatement): Boolean {
        return oldItem == newItem
    }
}