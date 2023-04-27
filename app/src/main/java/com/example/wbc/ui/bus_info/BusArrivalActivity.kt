package com.example.wbc.ui.bus_info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc.data.entity.BusArrivalResponse
import com.example.wbc.databinding.ActivityBusArrivalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusArrivalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusArrivalBinding
    private val busArrivalViewModel: BusArrivalViewModel by viewModels()
    private val adapter by lazy { BusArrivalAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusArrivalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewBusArrival.adapter = adapter
        binding.recyclerviewBusArrival.layoutManager = LinearLayoutManager(this)

        intent.getStringExtra("stationID")?.let { busArrivalViewModel.getBusArrivalTime(it) }

        busArrivalViewModel.busArrivalTimeResult.observe(this, Observer {
            adapter.submitList(it.body?.busArrivalList as MutableList<BusArrivalResponse>)
        })
    }
}