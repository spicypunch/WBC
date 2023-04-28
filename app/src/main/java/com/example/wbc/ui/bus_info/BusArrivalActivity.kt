package com.example.wbc.ui.bus_info

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc.data.entity.BusInfoEntity
import com.example.wbc.databinding.ActivityBusArrivalBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusArrivalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusArrivalBinding
    private val busArrivalViewModel: BusArrivalViewModel by viewModels()
    private val adapter by lazy { BusArrivalAdapter() }
    private var busList = mutableListOf<BusInfoEntity>()
    private var map = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusArrivalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewBusArrival.adapter = adapter
        binding.recyclerviewBusArrival.layoutManager = LinearLayoutManager(this)

        intent.getStringExtra("stationID")?.let {
            busArrivalViewModel.getBusArrivalTime(it)
        }

        busArrivalViewModel.busArrivalTimeResult.observe(this, Observer {
            if (it.body?.busArrivalList == null) {
                Toast.makeText(this, "도착 예정 버스가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                for (i in it.body?.busArrivalList!!) {
                    busArrivalViewModel.getBusName(i.routeId)
                    Log.e("i", "$i")
                    busList.add(BusInfoEntity(i.routeId.toString(), i.predictTime1, i.predictTime2))
                }
            }
        })

        busArrivalViewModel.busName.observe(this, Observer {
            if (it.body?.busRouteInfoItem != null) {
                for (i in it.body?.busRouteInfoItem!!) {
                    Log.e("시발", i.routeId.toString())
                    Log.e("좋같네", i.routeName)
                    map.put(i.routeId.toString(), i.routeName)
                }
            }
            replaceBusName()
        })
    }

    private fun replaceBusName() {
        busList.forEach {
            Log.e("기존 데이터", it.busNum)
            Log.e("새로 넣을 데이터", map[it.busNum]!!)
//            it.busNum = map[it.busNum]!!
        }
//        adapter.submitList(busList)
    }
}