package com.jm.wbc.ui.bus_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jm.wbc.listener.BookmarkClickListener
import com.jm.wbc.databinding.ItemBusArrivalBinding
import com.google.firebase.auth.FirebaseAuth
import com.jm.wbc.R
import com.jm.wbc.data.entity.BusInfoEntity

class BusArrivalAdapter(private val listener: BookmarkClickListener): ListAdapter<BusInfoEntity, BusArrivalAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBusArrivalBinding, private val listener: BookmarkClickListener) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BusInfoEntity) {
            binding.data = item

            binding.imageBookmark.setOnClickListener {
               if (FirebaseAuth.getInstance().currentUser == null) {
                   listener.onClick(null)
               }
                listener.onClick(item)
                Glide.with(binding.root).load(R.drawable.bookmark_complete).into(binding.imageBookmark)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBusArrivalBinding = ItemBusArrivalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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