package com.example.wbc.ui.bus_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.data.entity.BusArrivalList
import com.example.wbc.data.entity.BusInfoEntity
import com.example.wbc.databinding.ItemBusArrivalBinding


class BusArrivalAdapter() : ListAdapter<BusInfoEntity, BusArrivalAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBusArrivalBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BusInfoEntity) {
            binding.data = item

            binding.imageBookmark.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBusArrivalBinding = ItemBusArrivalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
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