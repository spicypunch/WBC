package com.example.wbc.ui.bus_info

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.listener.BookmarkClickListener
import com.example.wbc.data.entity.BusInfoEntity
import com.example.wbc.databinding.ItemBusArrivalBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class BusArrivalAdapter(private val listener: BookmarkClickListener
) : ListAdapter<BusInfoEntity, BusArrivalAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBusArrivalBinding, private val listener: BookmarkClickListener) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BusInfoEntity) {
            binding.data = item

            binding.imageBookmark.setOnClickListener {
               if (FirebaseAuth.getInstance().currentUser == null) {
                   listener.onClick(null)
               }
                listener.onClick(item)
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
                return oldItem.busNum == newItem.busNum
            }

            override fun areContentsTheSame(oldItem: BusInfoEntity, newItem: BusInfoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}