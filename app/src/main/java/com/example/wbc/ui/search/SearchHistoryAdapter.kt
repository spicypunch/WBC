package com.example.wbc.ui.search

import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.databinding.ItemHistoryBinding
import com.example.wbc.entity.SearchHistoryEntity

class SearchHistoryAdapter() : ListAdapter<SearchHistoryEntity, SearchHistoryAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: SearchHistoryEntity) {
            binding.data = item
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchHistoryEntity>() {

            override fun areItemsTheSame(oldItem: SearchHistoryEntity, newItem: SearchHistoryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchHistoryEntity, newItem: SearchHistoryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}