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
    private var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusArrivalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewBusArrival.adapter = adapter
        binding.recyclerviewBusArrival.layoutManager = LinearLayoutManager(this)

        val stationID = intent.getStringExtra("stationID")
        if (stationID != null) {
            busArrivalViewModel.getBusArrivalTime(stationID)
        }

        /**
         * 정류장 ID를 이용해 해당 정류장에 도착 예정인 버스의 목록을 조회한다.
         * 버스 도착 예정 API가 가진 단점으로는 응답 내용에 버스의 번호가 포함이 안 된다.
         * 버스의 번호 대신 해당 버스가 가진 고유 routeId를 가지기 때문에 routeId로 버스 정보 조회 API 요청을 한다.
         * 그리고 일단 routeID와 도착예정시간1, 도착예정시간2를 BusInfoEntity에 저장해준다.
         */
        busArrivalViewModel.busArrivalTimeResult.observe(this, Observer {
            if (it.body?.busArrivalList == null) {
                Toast.makeText(this, "도착 예정 버스가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                for (i in it.body?.busArrivalList!!) {
                    busArrivalViewModel.getBusName(i.routeId)
                    busList.add(BusInfoEntity(i.routeId.toString(), i.predictTime1, i.predictTime2))
                }
            }
        })

        /**
         * 위에서 요청한 버스 정보 조회 API의 결과값을 받아온다.
         * map에 routId와 routeName을 키와 값으로 각각 저장해준다.
         * busList.size == cnt를 비교하는 조건문을 걸지 않을 경우
         * map에 값을 전부 넣기 전에 replaceBusName 함수가 실행되고,
         * NPE이 발생하기 때문에 리스트 사이즈와 맵에 데이터를 저장한 횟수를 비교해 준다.
         */
        busArrivalViewModel.busName.observe(this, Observer {
            if (it.body?.busRouteInfoItem != null) {
                for (i in it.body?.busRouteInfoItem!!) {
                    map.put(i.routeId.toString(), i.routeName)
                    cnt++
                }
            }
            if (busList.size == cnt) {
                replaceBusName()
            }
        })

        binding.fabRefresh.setOnClickListener {
            busList.clear()
            cnt = 0
            busArrivalViewModel.getBusArrivalTime(stationID!!)
        }
    }

    /**
     * busList에 넣었던 routeId를 맵의 키값으로 이용해 routeName(버스 번호)를 조회하고 busList의 busNum에 넣어준다.
     */

    private fun replaceBusName() {
        busList.forEach {
            it.busNum = map[it.busNum]!!
        }
        adapter.submitList(busList)
    }
}