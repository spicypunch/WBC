package com.example.wbc.ui.bus_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.databinding.ItemBusArrivalBinding


class BusArrivalAdapter() : ListAdapter<BusArrivalResponse, BusArrivalAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBusArrivalBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BusArrivalResponse) {
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

        val diffUtil = object : DiffUtil.ItemCallback<BusArrivalResponse>() {

            override fun areItemsTheSame(oldItem: BusArrivalResponse, newItem: BusArrivalResponse): Boolean {
                return oldItem.body == newItem.body
            }

            override fun areContentsTheSame(oldItem: BusArrivalResponse, newItem: BusArrivalResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}