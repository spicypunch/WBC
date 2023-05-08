package com.example.wbc.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.data.entity.BusInfoEntity
import com.example.wbc.databinding.ItemBookmarkBinding
import com.example.wbc.listener.BookmarkClickListener
import com.google.firebase.auth.FirebaseAuth

class BookmarkAdapter(private val listener: BookmarkClickListener) : ListAdapter<BusInfoEntity, BookmarkAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBookmarkBinding, private val listener: BookmarkClickListener) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BusInfoEntity) {
            binding.data = item

            binding.imageDelete.setOnClickListener {
                if (FirebaseAuth.getInstance().currentUser == null) {
                    listener.onClick(null)
                }
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBookmarkBinding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<BusInfoEntity>() {

            override fun areItemsTheSame(oldItem: BusInfoEntity, newItem: BusInfoEntity): Boolean {
                return (oldItem.busNum == newItem.busNum || oldItem.predictTime1 == newItem.predictTime1 || oldItem.predictTime2 == newItem.predictTime2)
            }

            override fun areContentsTheSame(oldItem: BusInfoEntity, newItem: BusInfoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}