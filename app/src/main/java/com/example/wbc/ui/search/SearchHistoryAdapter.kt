package com.example.wbc.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.databinding.ItemHistoryBinding
import com.example.wbc.data.entity.SearchHistoryEntity

class SearchHistoryAdapter(private val listener: ItemClickListener) : ListAdapter<SearchHistoryEntity, SearchHistoryAdapter.MyViewHolder>(diffUtil) {

    class MyViewHolder(private val binding: ItemHistoryBinding, private val listener: ItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root
        fun bind(item: SearchHistoryEntity) {
            binding.data = item

            itemView.setOnClickListener {
                Intent(root.context, com.example.wbc.ui.map.MapActivity::class.java).apply {
                    putExtra("address", item.history)
                }.run { root.context.startActivity(this) }
            }

            binding.imageCancel.setOnClickListener {
                listener.onClick(item)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemHistoryBinding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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