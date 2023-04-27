package com.example.wbc.ui.bus_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.data.entity.BusArrivalList
import com.example.wbc.databinding.ItemBusArrivalBinding


class BusArrivalAdapter() : ListAdapter<BusArrivalList, BusArrivalAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBusArrivalBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BusArrivalList) {
            binding.data = item

            itemView.setOnClickListener {
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

        val diffUtil = object : DiffUtil.ItemCallback<BusArrivalList>() {

            override fun areItemsTheSame(oldItem: BusArrivalList, newItem: BusArrivalList): Boolean {
                return oldItem.routeId == newItem.routeId
            }

            override fun areContentsTheSame(oldItem: BusArrivalList, newItem: BusArrivalList): Boolean {
                return oldItem == newItem
            }
        }
    }
}