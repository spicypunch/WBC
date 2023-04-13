package com.example.wbc.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.databinding.ItemHistoryBinding
import com.example.wbc.entity.SearchHistoryEntity

class SearchHistoryAdapter() : ListAdapter<SearchHistoryEntity, SearchHistoryAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: SearchHistoryEntity) {
            binding.data = item

            itemView.setOnClickListener {

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHistoryBinding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
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